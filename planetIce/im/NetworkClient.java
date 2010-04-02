package planetIce.im;


/**
 * 
 * Example Client program using TCP.
 */

public class NetworkClient {

	final static String serverIPname = "localhost"; // server IP name
	final static int serverPort = 4444; // server port number

	public static void main(String args[]) {

		java.net.Socket sock = null; // Socket object for communicating
		java.io.PrintWriter pw = null; // socket output to server
		java.io.BufferedReader br = null; // socket input from server

		try {
			sock = new java.net.Socket(serverIPname, serverPort); // create  socket and connect
			pw = new java.io.PrintWriter(sock.getOutputStream(), true); // create reader and writer
			br = new java.io.BufferedReader(new java.io.InputStreamReader(sock.getInputStream()));

			System.out.println("Connecting to Server");
			
			pw.println("Connection test");
			String text = br.readLine();
			if (!text.equals("OK")) {
				System.out.println("Error connection. Message: " + text);
			} else {
				System.out.println("Connection.." + text + "..");
			}
			
			String[] input = { "Text", "Test" , "exit"};
			for (int i = 0; i < input.length; i++) {
				pw.println(input[i]);
				System.out.println("Message to server: " + input[i]);
				String answer = br.readLine(); // get data from the server
				System.out.println("Server >" + answer);

			}
			
			//pw.println("Message from the client"); // send msg to the server

			pw.close(); // close everything

			br.close();

			sock.close();

		} catch (Throwable e) {

			System.out.println("Error " + e.getMessage());

			e.printStackTrace();

		}

	}

}
