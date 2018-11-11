import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeoutException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.tools.jsonrpc.JsonRpcException;

import vlibtour.vlibtour_client_lobby_room.VLibTourLobbyRoomClient;
import vlibtour.vlibtour_lobby_room_api.InAMQPPartException;
import vlibtour.vlibtour_lobby_room_server.VLibTourLobbyServer;

public class TestScenarioLobbyRoom {
	
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
		c =  new Client("http://127.0.0.1:15672/api/", "guest", "guest");
	}
	
	@Test
	public void test() throws IOException, TimeoutException, JsonRpcException, InterruptedException, InAMQPPartException {
		VLibTourLobbyServer lobbyServer = new VLibTourLobbyServer();
		new Thread(lobbyServer).start();
		Assert.assertNotNull(c.getExchanges().stream().filter(q -> q.getName().equals(VLibTourLobbyServer.EXCHANGE_NAME)));
		Assert.assertNotNull(c.getBindings().stream().filter(b -> b.getRoutingKey().equals(VLibTourLobbyServer.BINDING_KEY)));
		VLibTourLobbyRoomClient lobbyClient1 = new VLibTourLobbyRoomClient();
		//Assert.assertEquals("URL1 : 1 1",lobbyClient1.createGroupAndJoinIt("1", "1"));
		VLibTourLobbyRoomClient lobbyClient2 = new VLibTourLobbyRoomClient();
		//Assert.assertEquals("URL1 : 2 1",lobbyClient2.createGroupAndJoinIt("2", "1"));
		VLibTourLobbyRoomClient lobbyClient3 = new VLibTourLobbyRoomClient();
		//Assert.assertEquals("URL2 : 1 1",lobbyClient3.joinAGroup("1", "1"));
		VLibTourLobbyRoomClient lobbyClient4 = new VLibTourLobbyRoomClient();
		//Assert.assertEquals("URL2 : 1 2",lobbyClient4.joinAGroup("1", "2"));

		Thread.sleep(5000); // wait for consume to read the message
		lobbyServer.close();
		lobbyClient1.close();
		lobbyClient2.close();
		lobbyClient3.close();
		lobbyClient4.close();
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException, IOException {
		new ProcessBuilder("rabbitmqctl", "stop_app").inheritIO().start().waitFor();
		new ProcessBuilder("rabbitmqctl", "stop").inheritIO().start().waitFor();
	}

}

