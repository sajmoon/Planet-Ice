package planetIce.test;

import junit.framework.TestCase;
import planetIce.network.MultiThreadedServer;
import planetIce.network.NetworkClient;

public class TestNetwork extends TestCase {

	public void testServer1() {
		System.out.println("Start Server");
		
		MultiThreadedServer server = new MultiThreadedServer(1232);
		Thread t = new Thread(server);
		t.start();
		System.out.println( "Server Text" + server.isStopped() );

	/*	try {
		    Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop(); */

		//server.run();
		System.out.println("Start a client");
		NetworkClient client1 = new NetworkClient(1232);

		client1.test();
		
		//new Thread(client1).start();
	
	}
}
