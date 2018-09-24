package vlibtour.vlibtour_lobby_room_server;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.jsonrpc.JsonRpcServer;

import vlibtour.vlibtour_lobby_room_api.InAMQPPartException;
import vlibtour.vlibtour_lobby_room_api.VLibTourLobbyService;

/**
 * This class implements the VLibTour lobby room service. This is the entry
 * point of the system for the clients when they want to start a tour.
 * <p>
 * When launched in its own process via a {@code java} command in shell script,
 * there is no call to {@link #close()}: the process is killed in the shell
 * script that starts this process. But, the class is a
 * {@link java.lang.Runnable} so that the lobby room server can be integrated in
 * another process.
 * 
 * @author Denis Conan
 */
public class VLibTourLobbyServer implements Runnable, VLibTourLobbyService {
	/**
	 * the name of the queue. This information does not need to be shared with
	 * clients, which send messages to the exchange (not to the queue), but the
	 * visibility is {@code package} for the JUnit tests.
	 */
	public static final String QUEUE_NAME = "lobby-room";
	/**
	 * the queue is durable in order to tolerate server volatility. It lasts until
	 * it is deleted.
	 */
	private static final boolean QUEUE_DURABLE = true;
	/**
	 * the queue is not exclusive in order to allow load balancing.
	 */
	private static final boolean QUEUE_EXCLUSIVE = false;
	/**
	 * the queue is not auto-deleted until it is no longer used.
	 */
	private static final boolean QUEUE_AUTODELETE = false;
	/**
	 * the connection to RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel to RabbitMQ broker.
	 */
	private Channel channel;
	/**
	 * the server object managing RPC for the client to start a tour. This server is
	 * run in a thread and is controlled by the administration server.
	 */
	private JsonRpcServer rpcServer;

	/**
	 * creates the lobby room server and the corresponding JSON server object.
	 * 
	 * @throws InAMQPPartException the exception thrown in case of a problem in the
	 *                             AMQP part.
	 */
	public VLibTourLobbyServer() throws InAMQPPartException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
			channel.queueDeclare(QUEUE_NAME, QUEUE_DURABLE, QUEUE_EXCLUSIVE, QUEUE_AUTODELETE, null);
			channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, BINDING_KEY);
			rpcServer = new JsonRpcServer(channel, QUEUE_NAME, VLibTourLobbyService.class, this);
		} catch (IOException | TimeoutException e) {
			throw new InAMQPPartException(e.getLocalizedMessage());
		}
		System.out.println("vlibtour lobby server: created");
	}

	/**
	 * gets the JSON RPC server.
	 * 
	 * @return the JSON RPC server.
	 */
	public JsonRpcServer getJsonRpcServer() {
		return rpcServer;
	}

	@Override
	public String createGroupAndJoinIt(final String groupId, final String userId) {
		// TODO : replace invalid URL by exceptions and add logging
		String url = "";
		if (groupId == null || groupId.equals("")) {
			return "group identifier is not valid (" + groupId + ")";
		}
		if (userId == null || userId.equals("")) {
			return "user identifier is not valid (" + userId + ")";
		}
		try {
			final String vhost = groupId;
			new ProcessBuilder("rabbitmqctl", "add_vhost", vhost).inheritIO().start().waitFor();
			new ProcessBuilder("rabbitmqctl", "list_vhosts").inheritIO().start().waitFor();
			url = setRabbitMQInfrastructureForUserInGroup(vhost, userId);
		} catch (Exception e) {
			// TODO throw an JsonRpcException?
			e.printStackTrace();
		}
		return url;
	}

	@Override
	public String joinAGroup(final String groupId, final String userId) {
		// TODO : replace invalid URL by exceptions and add logging
		String url = "";
		if (groupId == null || groupId.equals("")) {
			return "groupId is not valid (" + groupId + ")";
		}
		if (userId == null || userId.equals("")) {
			return "userId is not valid (" + userId + ")";
		}
		try {
			final String vhost = groupId;
			url = setRabbitMQInfrastructureForUserInGroup(vhost, userId);
		} catch (Exception e) {
			// TODO throw an JsonRpcException?
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * sets the RabbitMQ infrastructure for the connection of the user to the
	 * virtual host for the group communication during the tour.
	 * 
	 * @param vhost  the virtual host.
	 * @param userId the user identifier.
	 * @return the URL to be used by the user to connect to the group communication
	 *         system.
	 * @throws Exception the exception thrown in case of problem when configuring
	 *                   the broker.
	 */
	private String setRabbitMQInfrastructureForUserInGroup(final String vhost, final String userId) throws Exception {
		String tourId = vhost.substring(0, vhost.indexOf(GROUP_TOUR_USER_DELIMITER));
		String url = "";
		ConnectionFactory factory = new ConnectionFactory();
		factory.setVirtualHost(vhost);
		factory.setHost("localhost");
		final String password = new PasswordGenerator().generatePassword(8,
				Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
						new CharacterRule(EnglishCharacterData.LowerCase, 1),
						new CharacterRule(EnglishCharacterData.Digit, 1)));
		// TODO : we do not manage access control and consider that if the user
		// already exists then this is not the first connection
		// add_user not implemented in Hop => use shell command
		new ProcessBuilder("rabbitmqctl", "add_user", userId, password).inheritIO().start().waitFor();
		// set_permissions not implemented in Hop => use shell command
		new ProcessBuilder("rabbitmqctl", "set_permissions", "-p", vhost, userId, ".*", ".*", ".*").inheritIO().start()
				.waitFor();
		// add permissions to user guest in order to observe and log
		new ProcessBuilder("rabbitmqctl", "set_permissions", "-p", vhost, "guest", ".*", ".*", ".*").inheritIO().start()
				.waitFor();
		new ProcessBuilder("rabbitmqctl", "list_permissions", "-p", vhost).inheritIO().start().waitFor();
		Connection temporaryConnection = factory.newConnection();
		factory.setVirtualHost(vhost);
		Channel temporaryChannel = temporaryConnection.createChannel();
		temporaryChannel.exchangeDeclare(tourId, BuiltinExchangeType.TOPIC);
		url = "amqp://" + userId + ":" + password + "@" + factory.getHost() + ":" + factory.getPort() + "/" + vhost;
		temporaryChannel.close();
		temporaryConnection.close();
		return url;
	}

	/**
	 * creates the JSON RPC server and enters into the main loop of the JSON RPC
	 * server. The exit to the main loop is done when calling
	 * {@code stopLobbyRoom()} on the administration server. At the end of this
	 * method, the connectivity is closed.
	 */
	@Override
	public void run() {
		try {
			rpcServer.mainloop();
			// close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("vlibtour lobby server: terminated");
	}

	/**
	 * calls for the termination of the main loop if not already done and then
	 * closes the connection and the channel of this server.
	 * 
	 * @throws InAMQPPartException the exception thrown in case of a problem in the
	 *                             AMQP part.
	 */
	public void close() throws InAMQPPartException {
		try {
			if (rpcServer != null) {
				rpcServer.close();
			}
			if (channel != null) {
				channel.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (IOException | TimeoutException e) {
			throw new InAMQPPartException(e.getLocalizedMessage());
		}
		System.out.println("vlibtour lobby server: closed");
	}

	/**
	 * starts the lobby server.
	 * <p>
	 * When launched in its own process via a {@code java} command in shell script,
	 * there is no call to {@link #close()}: the process is killed in the shell
	 * script that starts this process.
	 * 
	 * @param args command line arguments
	 * @throws Exception all the potential problems (since this is a demonstrator,
	 *                   apply the strategy "fail fast").
	 */
	public static void main(final String[] args) throws Exception {
		VLibTourLobbyServer lobbyRoomServer = new VLibTourLobbyServer();
		new Thread(lobbyRoomServer).start();
	}
}
