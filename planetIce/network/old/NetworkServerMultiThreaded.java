package planetIce.network.old;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

import planetIce.Game.Game;

public class NetworkServerMultiThreaded implements Runnable {

    protected int serverPort = 1236;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    PlanetIceServerProtocol serverProtocol;
    HashMap<Integer, NetworkServerThread> clients;

    public NetworkServerMultiThreaded(int port, Game inputGame) {
	this.serverPort = port;
	clients = new HashMap<Integer, NetworkServerThread>();
	System.out.println("Create the server protocol");
	setServerProtocol(inputGame);
    }

    /**
     * Returns the server port.
     * @return porten
     */
    public int getServerPort() {
	return serverPort;
    }
    
    private void setServerProtocol(Game inputGame) {
	serverProtocol = new PlanetIceServerProtocol(inputGame);
    }
    private PlanetIceServerProtocol getServerProtocol() {
	return serverProtocol;
    }
    
    public void addGameToProtocol( Game g) {
	getServerProtocol().setGame(g);
    }

    /**
     * Run är det som körs när man skriver thread.start. Dvs (loopar konstant tror jag). Denna står och väntar på en
     * client ska försöka connecta. På raden clinetSocket = this.serverSocet.accept(). När den väl får en connection så
     * kör den och skapar en ny tråd för den.
     */
    public void run() {
	// synchronized(this){
	// this.runningThread = Thread.currentThread();
	// }
	openServerSocket();

	while (!isStopped()) {
	    Socket clientSocket = null;
	    try {
		clientSocket = this.serverSocket.accept();
		// TODO made the id unique, kan iaf vara bra att ha.
		int ID = new Random().nextInt(1000);
		NetworkServerThread client = new NetworkServerThread(clientSocket, ID,
			getServerProtocol());
		clients.put( ID, client);
		new Thread(client).start();
	    } catch (IOException e) {

		if (isStopped()) {
		    System.out.println("Server Stopped.");
		    return;
		} else {
		    throw new RuntimeException(
			    "Error accepting client connection", e);
		}
	    }
	}
	System.out.println("Server Stopped.");
    }

    public synchronized boolean isStopped() {
	return this.isStopped;
    }

    /**
     * Stoppar servern.
     */
    public synchronized void stop() {
	this.isStopped = true;
	try {
	    this.serverSocket.close();
	} catch (IOException e) {
	    throw new RuntimeException("Error closing server", e);
	}
    }

    /**
     * Startar server socket.
     */
    private void openServerSocket() {
	try {
	    this.serverSocket = new ServerSocket(this.serverPort);
	} catch (IOException e) {
	    throw new RuntimeException("Cannot open port " + this.serverPort, e);
	}
    }
    
    /**
     * Hur många som är anslutna
     * @return int Antala anslutna.
     */
    public int getUserCount() {
	return this.clients.size();
    }

}
