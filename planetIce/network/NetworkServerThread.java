package planetIce.network;

import java.io.IOException;
import java.net.Socket;

import planetIce.Game.Player;

public class NetworkServerThread extends NetworkExtend implements Runnable {

    

    public NetworkServerThread(Socket clientSocket, int serverID,
	    NetworkProtocol actions) {
		initNetwork(clientSocket, serverID, actions);

    }

    private void removeFromGame() {
	System.out.println("Remove user from player list");
	actionProtocol.removeFromGame(intID);
    }

    private void joinGame() {
	System.out.println("add user to player list");
	actionProtocol.joinGame(new Player("Player " + intID, intID));

    }

    public void run() {
	try {
	    System.out.println("NetworkServerThread ( " + this.serverText
		    + " ). New Client Connected");
	    connect();

	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    String inText = "";
	    
	    setOnline(true);
	    joinGame();
	    // System.out.println("Server startad. In Run(). do a while.");
	    // (inText = input.readLine()) != null
	    while (isOnline()) {
		// System.out.println("WorkerRunnable.run() while { ... }");
		readRequest();
	    }

	    removeFromGame();

	    output.close();
	    input.close();
	    System.out.println("NetworkServerThread ( " + this.serverText
		    + " ) : Requests processed. Server connection closed. ");

	} catch (IOException e) {
	    // report exception somewhere.
	    System.out.println("Error inte workerRunnable.. " + e.toString());
	    e.printStackTrace();
	}
    }

}
