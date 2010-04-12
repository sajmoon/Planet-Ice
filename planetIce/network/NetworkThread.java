package planetIce.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.Vector;

public class NetworkThread implements Runnable {
    Thread thread;
    ServerSocket serverSocket;
    int port = 1234;
    private Vector<Connected> connected; //vector är synchronizerad. Vilket är kul.
    
    
    public NetworkThread() {
	// TODO Auto-generated method stub
	thread = new Thread(this);

	try {
	    serverSocket = new ServerSocket(port);

	} catch (Exception e) {
	    //Failzor
	}

	thread.start();
	
	connected = new Vector<Connected>();

    }

    @Override
    public void run() {
	Socket socket = null;
	while (true) {
	    try {
		socket = serverSocket.accept();
	    } catch (Exception e) {
		
	    }
	    
	    if (socket != null) {
		connected.add(new Connected(socket, "Test1"));
	    }
	}
    }
    
    public void send( String text ) {
	
    }
    
    public String read() {
	return "";
    }
    
    private class Connected extends Thread {
	String name;
	NetworkThread t;
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	Stack<String> input;
	
	public Connected (Socket socket, String name) {
	    input = new Stack<String>();
	    this.name = name;
	    this.socket = socket;
	    try {
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	    } catch (Exception e) {
		System.out.println("Error Connected.");
		e.printStackTrace();
	    }
	    
	    this.start();
	    
	}
	
	public void send(String text) {
	    try {
		out.writeUTF(text);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
	public String getText() {
	    return input.pop();
	}
	
	public void run() {
	    while (true) {
		try {
		    Object o = in.readObject();
		    input.push((String)o);
		    
		    System.out.println((String)o);
		    
		} catch (Exception e) {
		    
		}
	    }
	}
    }

    public String getText() {
	// TODO Auto-generated method stub
	return connected.lastElement().input.pop();
    }
}
