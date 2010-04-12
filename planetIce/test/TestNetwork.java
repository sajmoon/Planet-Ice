package planetIce.test;

import java.net.ProtocolException;

import junit.framework.TestCase;
import planetIce.Game.Game;
import planetIce.network.NetworkClientThread;
import planetIce.network.NetworkServerMultiThreaded;

public class TestNetwork extends TestCase {

    int port = 2224;

    /**
     * Kollar typ bara att den går att starta.
     */
    public void testServer(int fail) {
	Game game = new Game();
	NetworkServerMultiThreaded server = new NetworkServerMultiThreaded(
		port, game);
	Thread t = new Thread(server);
	t.start();

	assertTrue(t.isAlive());
	assertEquals(server.getServerPort(), port);

	// server.stop();
    }

    /**
     * Kräver att det körs en server redan. Får det inte att fungera annars ;/
     */
    public void testServer1() {
	port++;
	System.out.println("Start Server");
	Game game = new Game();
	NetworkServerMultiThreaded server = new NetworkServerMultiThreaded(
		port, game);
	Thread t = new Thread(server);
	t.start();
	assertTrue(!server.isStopped());

	try {
	    Thread.sleep(50);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	// server.run();
	System.out.println("Start a client");
	NetworkClientThread client1 = new NetworkClientThread(port);

	Thread t1 = new Thread(client1);

	t1.start();

	try {
	    client1.send("Test");

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	System.out.println("Stopping Server");
	server.stop();

    }

    /**
     * Testa att skicka "Ping?" Svaret ska vara "Pong"
     */
    public void testServerPing() {
	port += 20;
	System.out.println("Start Server");
	Game game = new Game();
	NetworkServerMultiThreaded server = new NetworkServerMultiThreaded(
		port, game);
	Thread t = new Thread(server);
	t.start();
	assertTrue(!server.isStopped());

	try {
	    Thread.sleep(50);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	// server.run();
	System.out.println("Start a client");
	NetworkClientThread client1 = new NetworkClientThread(port);

	Thread t1 = new Thread(client1);

	t1.start();

	assertEquals(client1.sendRequest("Ping?"), "Pong!");

	System.out.println("Stopping Server");
	server.stop();
    }

    /**
     * Eftersom implementerade smarta protocol etc är överskattat så kör vi på att allt som skickas in skickas tillbaka
     * som Mirror: ( input ) Testa det på många kommandon irad.
     */
    public void testServerManyCommands() {
	port += 10;
	System.out.println("Start Server");
	Game game = new Game();
	NetworkServerMultiThreaded server = new NetworkServerMultiThreaded(
		port, game);
	Thread t = new Thread(server);
	t.start();
	assertTrue(!server.isStopped());

	try {
	    Thread.sleep(50);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	String[] text = new String[7];
	text[0] = "Första";
	text[1] = "Andra";
	text[2] = "Tredje";
	text[3] = "Fjärde";
	text[4] = "Femte";
	text[5] = "Sex";
	text[6] = "Sjuuuu";

	// server.run();
	System.out.println("Start a client");
	NetworkClientThread client1 = new NetworkClientThread(port);

	Thread t1 = new Thread(client1);

	t1.start();

	for (int i = 0; i < text.length; i++) {
	    assertEquals(client1.sendRequest(text[i]), "Mirror: (" + text[i]
		    + ")");
	}

	System.out.println("Stopping Server");
	server.stop();
    }

    public void test4() {
	port += 100;
	System.out.println("Start Server");
	Game game = new Game();
	NetworkServerMultiThreaded server = new NetworkServerMultiThreaded(
		port, game);
	Thread t = new Thread(server);
	t.start();
	assertTrue(!server.isStopped());

	try {
	    Thread.sleep(50);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	// server.run();
	System.out.println("Start a client");
	NetworkClientThread client1 = new NetworkClientThread(port);

	Thread t1 = new Thread(client1);

	t1.start();

	assertEquals(client1.sendRequest("test2"), "Mirror: (test2)");

	System.out.println("Stopping Server");
	server.stop();

    }
}
