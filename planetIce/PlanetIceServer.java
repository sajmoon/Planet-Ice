package planetIce;

import planetIce.network.MultiThreadedServer;

public class PlanetIceServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PlanetIceServer");
		System.out.println("Starting..");
		MultiThreadedServer server = null;
		int port = 1234;
		try {
			 server = new MultiThreadedServer(port);
			Thread t = new Thread(server);
			t.start();
			System.out.println("Server up and running in a fucking thread.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done..");

	}

}
