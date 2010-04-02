package planetIce.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**

 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
        	PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
  			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

  			while(true) {
  				long time = System.currentTimeMillis();
  				String inText = input.readLine();
  				if (inText.equals("exit")) {
  					break;
  				}
  				output.println( "Server got message (" + inText + ")");
  	            
  	            //output.write( "Server!! " + input.readLine() );
  	         
  			}
  	            // System.out.println("Tada");
  	            output.close();
  	            input.close();
  	            System.out.println("Request processed");
  			

            
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
