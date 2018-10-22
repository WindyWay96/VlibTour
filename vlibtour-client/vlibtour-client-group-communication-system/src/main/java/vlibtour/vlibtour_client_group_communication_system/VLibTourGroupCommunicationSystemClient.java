package vlibtour.vlibtour_client_group_communication_system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


/**
 * This class is the client application of the tourists.
 * 
 * @author Denis Conan
 */
public class VLibTourGroupCommunicationSystemClient {
	
	private Connection connection;
    private Consumer consumer;
	private Channel channel;
	private String routingKey;
	private String message;
	private String queueName;
	private String bindingKey;
	private String groupID;
	private String tourID;
	private String userID;
	
	private String EXCHANGE_NAME;
	private int nbMsgReceived = 0;
	private static AtomicInteger totalNbMsgReceived = new AtomicInteger(0);
	private static int c = 0;
	private int me;
	
	/**
	 * 
	 * @param groupID
	 * @param tourID
	 * @param userID
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public VLibTourGroupCommunicationSystemClient(final String groupID, final String tourID, final String userID, final String routingKey, final String message) throws IOException, TimeoutException {
		this.groupID = groupID;
		this.tourID = tourID;
		this.userID = userID;
		
		this.EXCHANGE_NAME = groupID + "_" + userID;
		this.queueName = tourID + "_" + userID;
		this.bindingKey = "*." + this.queueName +".#"; 
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		
		this.routingKey = routingKey;
		this.message = message;
	}
	
	public String addConsumer(Consumer consumer, String queueName, String bindingKey) throws IOException, TimeoutException {
		queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
		me = c++;
		consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(final String consumeTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Received" + me + " " + envelope.getRoutingKey() + ":" + message + "");
				nbMsgReceived++;
				totalNbMsgReceived.incrementAndGet();
			}
		};
		
		return channel.basicConsume(queueName, true, consumer);
	}
	
	public int getNbMsgReceived() {
		return nbMsgReceived;
	}
	public void publish() throws UnsupportedEncodingException, IOException {
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
		System.out.println("Sent " + routingKey + ":" + message + "");
	}
	
	public void close() throws IOException, TimeoutException{
		if(channel != null) {
			channel.close();
		}
		if(connection != null) {
			connection.close();
		}
	}
	
	private static String getRouting(final String[] strings) {
		if (strings.length < 1) {
			return "anonymous.info";
		}
		return strings[0];
	}
	
	private static String getMessage(final String[] strings) {
		if (strings.length < 2) {
			return "Hello World";
		}
		return Arrays.asList(strings).stream().skip(1).collect(Collectors.joining(" "));
	}
	
	/**
	 * get userID
	 * TODO HashMap or ArrayList
	 */
	
	private static String getUserID (final String[] strings) {
		if (strings.length < 1) {
            return ("!!!!!!");            
		}
		return Arrays.asList(strings).stream().skip(1).collect(Collectors.joining(" "));

	}
	
	/**
	 * getGroupID
	 */
	
	private static String getGroupID (final String[] strings) {
		if (strings.length < 1) {
            return ("!!!!!!");            
		}
		return Arrays.asList(strings).stream().skip(1).collect(Collectors.joining(" "));

	}
	
	/**
	 * getTourID
	 */
	
	private static String getTourID (final String[] strings) {
		if (strings.length < 1) {
            return ("!!!!!!");            
		}
		return Arrays.asList(strings).stream().skip(1).collect(Collectors.joining(" "));

	}

	public static void main(final String[] argv) throws Exception {
		String routingKey = getRouting(argv);
		String message = getMessage(argv);
		String groupID = getGroupID(argv);		
		String tourID = getTourID(argv);
		String userID = getUserID(argv);
		
		
		VLibTourGroupCommunicationSystemClient obj = new VLibTourGroupCommunicationSystemClient(groupID, tourID, userID, routingKey, message);
		obj.publish();
		obj.addConsumer(obj.consumer, obj.queueName, obj.bindingKey);
		obj.close();
	}
}