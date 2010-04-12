package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.Socket;

import planetIce.Game.Player;

public class NetworkServerThread implements Runnable {

    private boolean online = true; // stänger denna tråd om du sätter den till false
    protected Socket clientSocket = null;
    protected String serverText = null;
    protected int serverID = 0;
    PrintWriter output;
    BufferedReader input;
    NetworkProtocol actionProtocol;

    public NetworkServerThread(Socket clientSocket, int serverID,
	    NetworkProtocol actions) {
	this.clientSocket = clientSocket;
	this.serverText = "ServerID " + serverID;
	this.serverID = serverID;
	actionProtocol = actions;
    }

    public int getID() {
	return serverID;
    }

    /**
     * Läser text skickat från clienten.
     * 
     * @return texten vi ska få från clienten.
     */
    public String read() {
	try {
	    // System.out.println("WorkerRunnable.read()..");
	    String inputText = input.readLine();// = input.readLine();
	    if (inputText == null) {
		// Clienten kopplade typ ifrån eller?

	    } else {
		// System.out.println("Got text bitch!! (" + inputText + ")");
		//output.println(planetIce.network.Validation
		//	.getChecksum(inputText));
		// System.out.println("Sent response");
		return inputText;
	    }

	} catch (Exception e) {
	    System.err.println("ERR. NetworkServerThread. read:");
	    System.err.println("ERR. " + e.toString());
	    e.printStackTrace();
	}
	return null;
    }

    public void readRequest() {
	// System.out.println("WorkerRunnable.readRequest()..");

	try {
	    String inData = read();
	    if (inData == null) {
		// Klienten disconnectade ordentligt.
		// Stäng denna tråd typ
		online = false;
	    } else {
		send(actionProtocol.act(inData, serverID));
	    }
	} catch (ProtocolException e) {
	    System.out.println("NetworkServerThread.readRequest() "
		    + e.toString());
	    e.printStackTrace();
	}
    }

    private void removeFromGame() {
	System.out.println("Remove user from player list");
	actionProtocol.removeFromGame(serverID);
    }

    private void joinGame() {
	System.out.println("add user to player list");
	actionProtocol.joinGame(new Player("Player " + serverID, serverID));

    }

    public void run() {
	try {
	    System.out.println("NetworkServerThread ( " + this.serverText
		    + " ). New Client Connected");
	    output = new PrintWriter(clientSocket.getOutputStream(), true);
	    input = new BufferedReader(new InputStreamReader(clientSocket
		    .getInputStream()));

	    String inText = "";
	    online = true;

	    joinGame();
	    // System.out.println("Server startad. In Run(). do a while.");
	    // (inText = input.readLine()) != null
	    while (online) {
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

    /**
     * Send a message.
     * 
     * @param text
     * @throws java.net.ProtocolException
     */
    public void send(String text) throws java.net.ProtocolException {

	if (text == null) {
	    System.err
		    .println("NetworkServerThread().send() no text. text == null");
	} else {
	   // String checksum = planetIce.network.Validation.getChecksum(text);

	    output.println(text);
	    //try {
		//String serverMessage = input.readLine();
		//String serverChecksum = serverMessage;
		//if (!serverChecksum.equals(checksum)) {
	//    throw new ProtocolException(
	//		    "Not all data reached the server");
	//	}

	//    } catch (Exception e) {
	//	System.err.println("NetworkClientThread.sendToServer(String): "
	//		+ e.toString());
	//	e.printStackTrace();
	  //  }
	}
    }
}
