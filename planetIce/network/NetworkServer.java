package planetIce.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class NetworkServer extends Thread {
	int serverPort = 4444; // server port number
	boolean listening = true;
	ServerSocket serverSocket;
	ArrayList<NetworkServerThread> servers;

	public NetworkServer() {
		this(4444);

	}

	public NetworkServer(int i) {
		serverPort = i;
		serverSocket = null;
		servers = new ArrayList<NetworkServerThread>();
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
				//NetworkServerThread newServer = 
				Thread t = new Thread(new NetworkServerThread(serverSocket.accept())); 
				//new .start();
				//newServer.start();
				//servers.add ( newServer );
				//servers.get(0).start()
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getNumberOfUsers() {
		// TODO Auto-generated method stub
		return servers.size();
	}


}