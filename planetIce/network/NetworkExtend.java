package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkExtend {
    protected PrintWriter output;
    protected BufferedReader input;
    protected int intID = 0;
    NetworkProtocol actionProtocol;
    String serverText;
    int serverPort = 1234;
    
    protected Socket clientSocket = null;
    protected boolean online = true; // stänger denna tråd om du sätter den till false
    
    public void initNetwork(Socket ClientSocket, int serverID, NetworkProtocol actions) {
	this.clientSocket = clientSocket;
	this.serverText = "ServerID " + serverID;
	this.intID = serverID;
	actionProtocol = actions;
    }
    
    protected void setOnline(boolean status) {
	online = status;
    }
    
    protected boolean isOnline() {
	return online;
    }
    
    protected void connect() {
	try {
	    output = new PrintWriter(clientSocket.getOutputStream(), true);
	    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    System.out.println("network.Extend.connect " + e.toString());
	    e.printStackTrace();
	}
	    
    }
    

    protected void openSocket() {
	try {
	    this.clientSocket = new Socket("localhost", serverPort);
	} catch (IOException e) {
	    throw new RuntimeException("Cannot open port " + serverPort, e);
	}
    }
    
    /**
     * Send text to server, get a response back.
     * 
     * @param text The text to send to the server
     * @return The response from the server.
     */
    public synchronized String sendRequest(String text) {
	send(text);
	return read();
    }
    

    public int getID() {
	return intID;
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
		String outData = actionProtocol.act(inData, intID);
		if (outData != null) {
		    send(outData);
		}
	    }
	} catch (Exception e) {
	    System.out.println("NetworkServerThread.readRequest() "
		    + e.toString());
	    e.printStackTrace();
	}
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
	    //System.out.println("Inputtext! read server! : " + inputText);
	    if (inputText == null) {
		// Clienten kopplade typ ifrån eller?

	    } else {
		// System.out.println("Got text bitch!! (" + inputText + ")");
//		output.println(planetIce.network.Validation
//			.getChecksum(inputText));
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
    
    /**
     * Send a message.
     * 
     * @param text
     * @throws java.net.ProtocolException
     */
    public void send(String text) {

	if (text == null) {
	    System.err
		    .println("NetworkServerThread().send() no text. text == null");
	} else {
//	    String checksum = planetIce.network.Validation.getChecksum(text);

	    output.println(text);
//	    try {
//		String serverMessage = input.readLine();
//		String serverChecksum = serverMessage;
//		if (!serverChecksum.equals(checksum)) {
//		    throw new ProtocolException(
//			    "Not all data reached the server");
//		}

//	    } catch (Exception e) {
//		System.err.println("NetworkClientThread.sendToServer(String): "
//			+ e.toString());
//		e.printStackTrace();
//	    }
	}
    }



}
