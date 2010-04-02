package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkClientThread implements Runnable {

	Socket clientSocket;
	int serverPort = 1236;
	java.io.PrintWriter pw = null; // socket output to server
	java.io.BufferedReader br = null; // socket input from server
	
	public NetworkClientThread() {
		openClientSocket();
		try {
        	pw = new java.io.PrintWriter(clientSocket.getOutputStream(), true); // create reader and writer
			br = new java.io.BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
        	while (true) {
				pw.write("Hello");
				System.out.println("Client: " + br.readLine() );
			}
		} catch (Exception e) {
        	
        }
	}
	
    private void openClientSocket() {
        try {
            this.clientSocket = new Socket("127.0.0.1",serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + serverPort, e);
        }
    }

}
