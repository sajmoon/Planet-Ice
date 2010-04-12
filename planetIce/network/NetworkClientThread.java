package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.Socket;

public class NetworkClientThread extends NetworkExtend implements Runnable {

//    Socket clientSocket;
    
    public NetworkClientThread(int port) {
	serverPort = port;
	openSocket();
	connect();
	/*try {
	    output = new PrintWriter(clientSocket.getOutputStream(), true); // create reader and writer
	    input = new BufferedReader(new InputStreamReader(clientSocket
		    .getInputStream()));
	} catch (Exception e) {
	    e.printStackTrace();
	}*/
    }

    @Override
    public void run() {
	// TODO Auto-generated method stub
	while (true) {
	    try {
		Thread.sleep(10000);
		// Ping server. ;)
		long startTime = System.currentTimeMillis();
		System.out.println(this.sendRequest("Ping?"));
		long endTime = System.currentTimeMillis();
		System.out.println("Lagg: " + (endTime - startTime) + "ms");
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }



}
