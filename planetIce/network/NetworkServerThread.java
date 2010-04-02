package planetIce.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkServerThread extends Thread{
	private Socket socket = null;

	public NetworkServerThread(Socket socket) {
		// super("NetworkServerThread");
		this.socket = socket;
	}

	public void run() {

		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));

			String inputLine, outputLine;
			NetworkProtocoll actions = new NetworkProtocoll();
			// outputLine = actions.processInput(null);
			// out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Client: " + inputLine );
				outputLine = actions.processInput(inputLine);
				out.println(outputLine);
				System.out.println("Server: " + outputLine);
				if (outputLine.equals("exit"))
					break;
			}
			out.close();
			in.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
