package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

/**

 */
public class NetConnection implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;
	MultiThreadedServer server = null;
	PrintWriter output = null;

	public NetConnection(MultiThreadedServer server, Socket clientSocket,
			String serverID) {
		this.clientSocket = clientSocket;
		this.serverText = serverID;
		this.server = server;
		try {
			output = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error seting up a new pw NetConnection");
			e.printStackTrace();
		}
		server.addClient(this);

	}

	public void run() {
		try {

			BufferedReader input = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			while (true) {
				long time = System.currentTimeMillis();
				String inText = input.readLine();
				if (inText.equals("exit")) {
					break;
				} else {

					// output.println("Server got message (" + inText + ")");
					// inData.add( inText );
					server.send(inText);
					// System.out.println(inText);
					// System.out.println(stackData.size());
				}
				// output.write( "Server!! " + input.readLine() );

			}
			input.close();
			System.out.println("Request processed. End bithces..");

		} catch (IOException e) {
			// report exception somewhere.
			e.printStackTrace();
		}
	}

	public void sendText(String text) {
		output.println(text);
	}
}
