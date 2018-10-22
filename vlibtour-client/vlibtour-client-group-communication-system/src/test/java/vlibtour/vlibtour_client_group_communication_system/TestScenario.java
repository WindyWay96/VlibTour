package vlibtour.vlibtour_client_group_communication_system;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import com.rabbitmq.http.client.Client;


//import vlibtour.vlibtour_client_group_communication_system.VLibTourGroupCommunicationSystemClient;

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

		VLibTourGroupCommunicationSystemClient obj1 = new VLibTourGroupCommunicationSystemClient("gr1", "tour1", "usr1", "Fabian.Tobiac ", "message one");
		obj1.publish();
		VLibTourGroupCommunicationSystemClient obj2 = new VLibTourGroupCommunicationSystemClient("gr2", "tour2", "usr2", "Andrea.Orlean ", "message two");
		obj2.publish();
		// obj1.addConsumer(obj1.consumer, obj1.queueName, obj1.bindingKey);
		
		
	}

}