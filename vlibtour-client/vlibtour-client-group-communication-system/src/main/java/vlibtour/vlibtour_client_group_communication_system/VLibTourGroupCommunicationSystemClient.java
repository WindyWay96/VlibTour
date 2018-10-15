package vlibtour.vlibtour_client_group_communication_system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

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

	private Channel channel;
	private String routingKey;
	private String message;
	
	private String groupID;
	private String tourID;
	private String userID;
	
	private String EXCHANGE_NAME;
	
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
		consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(final String consumeTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Received" + " " + envelope.getRoutingKey() + ":" + message + "");
			}
		};
		
		return channel.basicConsume(queueName, true, consumer);
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
	
	public static void main(final String[] argv) throws Exception {
		VLibTourGroupCommunicationSystemClient obj = new VLibTourGroupCommunicationSystemClient("gr1", "tour1", "usr1000", "anonymous.info", "Hello");
		
	}
}
