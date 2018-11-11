package vlibtour.vlibtour_client_lobby_room;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.jsonrpc.JsonRpcClient;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;
import vlibtour.vlibtour_lobby_room_server.VLibTourLobbyServer;
import vlibtour.vlibtour_lobby_room_api.VLibTourLobbyService;

/**
 * This class is the client application of the tourists.
 * 
 * @author Denis Conan
 */
public class VLibTourLobbyRoomClient {
	
	/**
	 * the connection to the RabbitMQ broker.
	 */
	private Connection connection;
	/**
	 * the channel for producing and consuming.
	 */
	private Channel channel;
	/**
	 * the RabbitMQ JSON RPC client.
	 */
	private JsonRpcClient jsonRpcClient;
	/**
	 * the Fibonacci service.
	 */
	private VLibTourLobbyService client;

	/**
	 * default constructor of the RPC client.
	 * 
	 * @throws IOException
	 *             the communication problems.
	 * @throws TimeoutException
	 *             broker to long to connect to.
	 * @throws JsonRpcException
	 *             JSON problem in marshaling or un-marshaling.
	 */
	public VLibTourLobbyRoomClient() throws IOException, TimeoutException, JsonRpcException {
		// Create a new connection for the client		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		channel.exchangeDeclare(VLibTourLobbyServer.EXCHANGE_NAME, "direct");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, VLibTourLobbyServer.EXCHANGE_NAME, VLibTourLobbyServer.BINDING_KEY);

		jsonRpcClient = new JsonRpcClient(channel, VLibTourLobbyServer.EXCHANGE_NAME, VLibTourLobbyServer.BINDING_KEY);
		client = (VLibTourLobbyService) jsonRpcClient.createProxy(VLibTourLobbyService.class);
	}
	
	/**
	 * calls the createGroupAndJointIt service.
	 * 
	 * @param groupId id of the group.
	 * @param userId id of the participant.
	 * @return an URL, which is used to connect to the infrastructure for 
	 * 		   the group communication dedicated to the visit. 
	 */
	public String createGroupAndJoinIt(final String groupId, final String userId) {
		System.out.println(" [x] " + userId + " : Requesting creation of group (" + groupId + ")");
		String url = client.createGroupAndJoinIt(groupId, userId);
		System.out.println(" [.] Got '" + url + "'");
		return url;
	}

	/**
	 * calls the joinAGroup service.
	 * 
	 * @param groupId id of the group.
	 * @param userId id of the participant.
	 * @return an URL, which is used to connect to the infrastructure for 
	 * 		   the group communication dedicated to the visit. 
	 */

	public String joinAGroup(final String groupId, final String userId) {
		System.out.println(" [x] " + userId + " : Requesting joining the group (" + groupId + ")");
		String url = client.joinAGroup(groupId, userId);
		System.out.println(" [.] Got '" + url + "'");
		return url;
	}

	/**
	 * closes the JSON RPC client, the channel and the connection with the broker.
	 * 
	 * @throws IOException
	 *             communication problem.
	 * @throws TimeoutException
	 *             broker to long to communicate with.
	 */
	public void close() throws IOException, TimeoutException {
		jsonRpcClient.close();
		if (channel != null) {
			channel.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	/**
	 * the main method of the example.
	 * 
	 * @param argv
	 *            the command line argument is the number of the call.
	 * @throws Exception
	 *             communication problem with the broker.
	 */
	public static void main(final String[] argv) throws Exception {
		String groupId = argv[0];
		String userId = argv[1];
		VLibTourLobbyRoomClient lobbyroomClient = new VLibTourLobbyRoomClient();
		lobbyroomClient.createGroupAndJoinIt(groupId, userId);
		lobbyroomClient.joinAGroup(groupId, "2");
		lobbyroomClient.close();
	}
}
