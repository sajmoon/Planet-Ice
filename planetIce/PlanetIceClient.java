package planetIce;

import java.util.Random;

import planetIce.network.NetworkClientThread;

public class PlanetIceClient {

    protected boolean waitingForKeyPress = true;

    public PlanetIceClient() {
	System.out.println("PlanetIceClient");
	System.out.println("Starting..");

	Networks();

	// Graphics();
    }

    public void Networks() {
	NetworkClientThread client = new NetworkClientThread(1234);
	Thread t = new Thread(client);
	t.start();

	// Testkod-ish
	try {
	    if (client.sendRequestToServer("Ping?").equals("Pong!")) {
		System.out.println("Connected");
	    } else {
		System.out.println("What? Doesnt work?");
	    }

	    for (int i = 0; i < 10; i++) {

		Thread.sleep(4000);
		int r1 = new Random().nextInt(3);
		int r2 = new Random().nextInt(3);

		//System.out.println(client.sendRequestToServer("move "+  r1 + " " + r2));
		client.send("move "+  r1 + " " + r2);
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

    /**
     * Graphical USEr Interface
     * 
     * @deprecated
     */
    public void Graphics() {

    }

}
