package planetIce;

import java.util.Random;

import planetIce.Game.Game;
import planetIce.network.NetworkClientThread;

public class PlanetIceClient {

	protected boolean waitingForKeyPress = true;
	private Game g;
	private NetworkClientThread client = null;
	private String clientName;

	public PlanetIceClient() {
		clientName = "Client: " + (new Random()).nextInt();
		
		System.out.println("PlanetIceClient");
		System.out.println("Starting..");

		Networks();

		// Graphics();

		CLI();
		
		sendTest();

	}

	public void Networks() {

		client = new NetworkClientThread(
				planetIce.conf.conf.port);
		
		Thread t = new Thread(client);
		
		
		t.start();

	}

	/**
	 * Graphical USEr Interface
	 * 
	 * @deprecated
	 */
	public void Graphics() {

	}

	public void CLI() {
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (input != "exit") {
			// loopa tills det är exit.
			System.out.print("$");

			try {
				input = br.readLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
	public void sendTest() {
		// Testkod-ish
		try {

			/*
			 * if (client.sendRequestToServer("Ping?").equals("Pong!")) {
			 * System.out.println("Connected"); } else {
			 * System.out.println("What? Doesnt work?"); }
			 */

			for (int i = 0; i < 100; i++) {

				Thread.sleep(4000);
				int r1 = new Random().nextInt(10);
				int r2 = new Random().nextInt(10);

				// System.out.println(client.sendRequestToServer("move "+ r1 +
				// " " + r2));
				client.send(clientName + " move " + r1 + " " + r2);
			}

			/**
			 * System.out.println(client.sendRequestToServer("Hello.."));
			 * System.out.println(client.sendRequestToServer("TEST"));
			 * System.out.println(client.sendRequestToServer("TEST"));
			 * System.out.println(client.sendRequestToServer("hejsan"));
			 **/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("What? The netwokr doesnt work!");
			// e.printStackTrace();
		}

	}
}
