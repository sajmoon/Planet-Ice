package planetIce.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class MultiThreadedServer implements Runnable {

	protected int serverPort = 1236;
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	private Queue<String> inData;
	private Vector<NetConnection> clientlist = null;
	Thread t = null;

	public MultiThreadedServer(int port) {
		System.out.println("MultiThreadedServer() Start");
		inData = new LinkedList<String>();
		t = new Thread(this);
		this.serverPort = port;

		clientlist = new Vector<NetConnection>();

		t.start();

		System.out.println("MultiThreadedServer() efter t.start");

		while (true) {
			// System.out.println("MultiThreadedServer().loop");
			
			if (inData.size() > 0) {

				String text = inData.poll();
				//System.out.println("X MultiThreadedServer(). size " + inData.size());
				sendToAll(text);
			}
			//System.out.println("MultiThreadedServer(). size " + inData.size());
			
		}
	}

	public int getListenPort() {
		return serverPort;
	}

	public synchronized String getMessage() {
		return inData.poll();
	}

	public void sendToAll(String text) {
		
		System.out.println("sendToAll() " + text);
		Iterator<NetConnection> it = clientlist.iterator();

		while (it.hasNext()) {
			it.next().sendText(text);
		}
	}

	public void send(String text) {
		//System.out.println("MultiThreadedServer().send(): " + text);
		inData.add(text);
	}

	public boolean isEmpty() {
		return inData.isEmpty();
	}

	public void run() {

		openServerSocket();

		while (!isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (isStopped()) {
					System.out.println("Server Stopped.");
					return;
				}
				throw new RuntimeException("Error accepting client connection",
						e);
			}
			NetConnection r = new NetConnection(this, clientSocket, "ServerID");
			new Thread(r).start();
		}
		System.out.println("Server Stopped.");
	}

	public synchronized boolean isStopped() {
		return this.isStopped;
	}

	public void addClient(NetConnection con) {
		this.clientlist.add(con);
	}

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + this.serverPort, e);
		}
	}

}
