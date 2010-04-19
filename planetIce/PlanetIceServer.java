package planetIce;

import planetIce.Game.Game;
import planetIce.network.MultiThreadedServer;

public class PlanetIceServer {

	MultiThreadedServer server;
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

		// GamePlay();

		Networks();
		System.out.println("Test");
		long endTime = System.currentTimeMillis();

		System.out.println("Up and running. Total time to start: "
				+ (endTime - startTime) + "ms");
		System.out.println("Servern körs på port " + server.getListenPort()
				+ "\n");

	}

	/*
	 * private void GamePlay() { game = new Game(); Thread t = new Thread(game);
	 * t.start(); }
	 */

	private void Networks() {
		// Starta nät-trådar.
		try {
			// serverGame = new Game();
			server = new MultiThreadedServer(serverPort);
			Thread t = new Thread(server);
			t.start();
			System.out.println("Server up and running in a fucking thread.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
