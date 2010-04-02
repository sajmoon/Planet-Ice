package planetIce.network;

import java.io.IOException;
import java.net.ServerSocket;

public class NetworkServer extends Thread {
	int serverPort = 4444; // server port number
	boolean listening = true;
	ServerSocket serverSocket;

	public NetworkServer() {
		serverSocket = null;

		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + serverPort + ".");
			listening = false;
			System.exit(-1);
		}

		run();

	}

	public int getPort() {
		return serverPort;
	}

	public void run() {
		while (listening) {
			try {
				new NetworkServerThread(serverSocket.accept()).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}