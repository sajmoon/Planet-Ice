package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.Socket;

public class NetworkClientThread implements Runnable {

	Socket clientSocket;
	int serverPort = planetIce.conf.conf.port; // standard port
	PrintWriter pw = null; // socket output to server
	BufferedReader br = null; // socket input from server
	String id = "";
	
	public NetworkClientThread(int port) {
		serverPort = port;
		openClientSocket();
		try {
			pw = new PrintWriter(clientSocket.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(clientSocket
					.getInputStream()));
			id =  br.readLine() ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (true) {

			try {
				String text = br.readLine();
				System.out.println(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error br.readline()");
				e.printStackTrace();
			}

		}

	}

	/**
	 * sends a message to the server, and checks that all data came to the
	 * server
	 * 
	 * @param text
	 *            Texten vi ska skicka.
	 * @throws java.net.ProtocolException
	 *             Om t ex all data inte kom fram etc.
	 */
	public synchronized void send(String text)
			throws java.net.ProtocolException {
		pw.println(id + " " + text);
	}

	private void openClientSocket() {
		try {
			this.clientSocket = new Socket("localhost", serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + serverPort, e);
		}
	}

}
