package vlibtour.vlibtour_client_group_communication_system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

import vlibtour.vlibtour_map_viewer.MapDemo;
import vlibtour.vlibtour_visit_emulation.GraphOfPositionsForEmulation;


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
	
	public static final String EXCHANGE_NAME =  "gr1_usr1";
	private int nbMsgReceived = 0;
	private static AtomicInteger totalNbMsgReceived = new AtomicInteger(0);
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
		this.queueName = tourID + "_" + userID; 
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		this.routingKey = routingKey;
		this.message = message;
		
	}
	
	public void addConsumer(Consumer consumer, String queueName, String bindingKey) throws IOException, TimeoutException {
		this.bindingKey = bindingKey;
		channel.queueDeclare(queueName, true, true, true, null);
		channel.queueBind(this.queueName, EXCHANGE_NAME, bindingKey);
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(final String consumeTag, final Envelope envelope, 
					final AMQP.BasicProperties properties, final byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + " " + envelope.getRoutingKey() + "':'" + message + "'");
				nbMsgReceived++;
				totalNbMsgReceived.incrementAndGet();
			}
		};
		channel.basicConsume(queueName, true, consumer);
		
	}

	public String getQueueName() {
		return this.queueName;
	}
	public String getBindingKey() {
		return this.bindingKey;
	}
//	public String getRoutingKey() {
//		return this.routingKey;
//	}
	public int getNbMsgReceived() {
		return nbMsgReceived;
	}
	public static int getTotalNbMsgReceived() {
		return totalNbMsgReceived.get();
	}
	public Consumer getConsumer() {
		return consumer;
	}
	public void publish() throws UnsupportedEncodingException, IOException {
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
	}
	
	public void close() throws IOException, TimeoutException{
		if(channel != null) {
			channel.close();
		}
		if(connection != null) {
			connection.close();
		}
	}
	
	private static String getRoutingKey(final String[] strings) {
		if (strings.length < 1) {
			return "anonymous.info";
		}
		return strings[0];
	}
	
	/**
	 * Get the GPS location of user
	 */
	
	private static String getMessage(final String[] strings) {
		if (strings.length < 2) {
			return "Hello World";
			

		}
		return Arrays.asList(strings).stream().skip(1).collect(Collectors.joining(" "));
	}

	public static void main(final String argc, final String[] argv) throws Exception {
		String routingKey = getRoutingKey(argv);
		String message = getMessage(argv);
		
		VLibTourGroupCommunicationSystemClient obj = new VLibTourGroupCommunicationSystemClient("gr1", "tour1", "usr1", routingKey, message);
		obj.addConsumer(obj.consumer, obj.queueName, argc);
		obj.publish();
		Thread.sleep(4000);
		obj.close();
	}
}