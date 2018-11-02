import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.rabbitmq.http.client.Client;

import vlibtour.vlibtour_client_group_communication_system.VLibTourGroupCommunicationSystemClient;
public class TestScenario {
	
	private static Client c;
	
	@BeforeClass
	public static void setUp() throws IOException, InterruptedException, URISyntaxException {
		try {
			new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
		} catch (IOException | InterruptedException e) {
		}
	Thread.sleep(1000);
	new ProcessBuilder("rabbitmq-server", "-detached").inheritIO().start().waitFor();
	new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
	new ProcessBuilder("rabbitmqctl", "reset").inheritIO().start().waitFor();
	new ProcessBuilder("rabbitmqctl", "start_app").inheritIO().start().waitFor();
	c = new Client("http://127.0.0.1:15672/api/", "guest", "guest");
}
	@Test
	public void test() throws IOException, TimeoutException, InterruptedException, ExecutionException{
		//Receiver rec1 = new Receiver("hello");
		Assert.assertNotNull(c.getExchanges().stream().filter(q -> q.getName().equals(VLibTourGroupCommunicationSystemClient.EXCHANGE_NAME)));
		VLibTourGroupCommunicationSystemClient obj1 = new VLibTourGroupCommunicationSystemClient("gr1", "tour1", "usr1", "tour1_usr1", "message one");
		obj1.addConsumer(obj1.getConsumer(), obj1.getQueueName(), "tour1_usr1");
		obj1.publish();
		
		VLibTourGroupCommunicationSystemClient obj2 = new VLibTourGroupCommunicationSystemClient("gr1", "tour1", "usr2", "tour1_usr2", "message two");
		obj2.addConsumer(obj2.getConsumer(), obj2.getQueueName(), "tour1_usr2");
		obj2.publish();
		
		Thread.sleep(3000);
		
		Assert.assertEquals(1, obj1.getNbMsgReceived());
		Assert.assertEquals(1, obj2.getNbMsgReceived());
		
		obj1.close();
		obj2.close();
		
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException, IOException {
		new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
	}

}