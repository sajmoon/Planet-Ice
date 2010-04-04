package planetIce;

import planetIce.Game.Game;
import planetIce.network.NetworkServerMultiThreaded;

public class PlanetIceServer {

    NetworkServerMultiThreaded server;
    Game game;
    int serverPort = 1234;;
    Game serverGame;

    /**
     * Starts a server
     */
    public PlanetIceServer() {
	long startTime = System.currentTimeMillis();
	System.out.println("PlanetIceServer");
	System.out.println("Starting..");
	server = null;

	GamePlay();
	
	Networks();
	
	long endTime = System.currentTimeMillis();
	
	System.out.println("Up and running. Total time to start: " + (endTime - startTime ) +"ms");
	System.out.println("Servern körs på port "  + server.getServerPort() + "\n");
	
    }

    private void GamePlay() {
	game = new Game();
	Thread t = new Thread(game);
	t.start();
    }

    private void Networks() {
	// Starta nät-trådar.
	try {
	    serverGame = new Game();
	    server = new NetworkServerMultiThreaded(serverPort, serverGame);
	    Thread t = new Thread(server);
	    t.start();
	    System.out.println("Server up and running in a fucking thread.");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
