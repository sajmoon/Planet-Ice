package planetIce.network;

import java.io.IOException;

/**
 * 
 * Example Client program using TCP.
 */

public class NetworkClient {

	final static String serverIPname = "localhost"; // server IP name
	static int serverPort = 1236; // server port number
	java.net.Socket sock = null; // Socket object for communicating
	java.io.PrintWriter pw = null; // socket output to server
	java.io.BufferedReader br = null; // socket input from server
	
	public int getPort() {
		return serverPort;
	}

	public void connectionTest() throws IOException {
		System.out.println("Connecting to Server");

		pw.println("Connection test");
		String text = br.readLine();
		if (!text.equals("OK")) {
			System.out.println("Error connection. Message: " + text);
		} else {
			System.out.println("Connection.." + text + "..");
		}
	}

	public NetworkClient(int port) {
		serverPort = port;
		try {
			sock = new java.net.Socket(serverIPname, serverPort); // create socket and connect
			pw = new java.io.PrintWriter(sock.getOutputStream(), true); // create reader and writer
			br = new java.io.BufferedReader(new java.io.InputStreamReader(sock.getInputStream()));

		} catch (Throwable e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();

		}
	}

	public void sendToServer(String text) {
		pw.println(text);
	}
	
	public String readFromServer() throws IOException {
		return br.readLine(); // get data from the server;
	}
	
	public String serverRequest(String text) throws IOException {
		sendToServer(text);
		return br.readLine();
	}

	public void test() {
		int j = 0;
		try {
			String[] input = { "Text", "Test", "exit" };
			for (int i = 0; i < input.length; i++) {
				sendToServer(input[i]);
				System.out.println("Message to server: " + input[i]);
				String answer = readFromServer();
				System.out.println("Server >" + answer);

			}
		} catch (Exception e) {
			System.out.println("Client Error (test) " + e.toString());
		}
	}

	public void closeConnection() throws IOException {

		pw.close(); // close everything

		br.close();

		sock.close();
	}

}
