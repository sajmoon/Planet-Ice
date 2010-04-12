package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.Socket;

public class NetworkClientThread implements Runnable {

    Socket clientSocket;
    int serverPort = 1236; //standard port
    PrintWriter pw = null; // socket output to server
    BufferedReader br = null; // socket input from server

    public NetworkClientThread(int port) {
	serverPort = port;
	openClientSocket();
	try {
	    pw = new PrintWriter(clientSocket.getOutputStream(), true); // create reader and writer
	    br = new BufferedReader(new InputStreamReader(clientSocket
		    .getInputStream()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public void run() {
	// TODO Auto-generated method stub
	while (true) {
	    try {
		Thread.sleep(10000);
		// Ping server. ;)
		long startTime = System.currentTimeMillis();
		System.out.println(this.sendRequestToServer("Ping?"));
		long endTime = System.currentTimeMillis();
		System.out.println("Lagg: " + ( endTime-startTime ) + "ms");
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

    /**
     * sends a message to the server, and checks that all data came to the server
     * 
     * @param text Texten vi ska skicka.
     * @throws java.net.ProtocolException Om t ex all data inte kom fram etc.
     */
    public synchronized void send(String text)
	    throws java.net.ProtocolException {
	String checksum = planetIce.network.Validation.getChecksum(text);

	// pw.write(text);
	pw.println(text);

	/*try {
	    String serverMessage = br.readLine();
	    if (serverMessage == null) {
		// user typ disconnectade
	    } else {
		String serverChecksum = serverMessage;
		if (!serverChecksum.equals(checksum)) {
		    throw new ProtocolException(
			    "Not all data reached the server");
		}
	    }

	} catch (Exception e) {
	    System.err.println("NetworkClientThread.sendToServer(String): "
		    + e.toString());
	    e.printStackTrace();
	}*/
    }

    /**
     * Läser text skickat från servern.
     * 
     * @return Det som kom från servern
     */
    public synchronized String read() {
	try {
	    String input = br.readLine();
	    if (input == null) {
		// user typ disconnectade
	    } else {
	//	pw.println(planetIce.network.Validation.getChecksum(input));
		return input;
	    }
	} catch (IOException e) {
	    System.err
		    .println("ERR. NetworkClientThread. sendToServer(String):");
	    System.err.println("ERR. " + e.toString());
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * Send text to server, get a response back.
     * 
     * @param text The text to send to the server
     * @return The response from the server.
     */
    public synchronized String sendRequestToServer(String text) {

	try {
	    send(text);
	    return read();
	} catch (ProtocolException e1) {
	    // The message did not reach the server as planed.
	    System.err
		    .println("Err. NetworkClientThread.sendRequestToServer() "
			    + e1.toString());
	}
	return null;
    }

    private void openClientSocket() {
	try {
	    this.clientSocket = new Socket("localhost", serverPort);
	} catch (IOException e) {
	    throw new RuntimeException("Cannot open port " + serverPort, e);
	}
    }

}
