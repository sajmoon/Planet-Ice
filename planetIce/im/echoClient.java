package planetIce.im;

import java.net.*;
import java.io.*;

public class echoClient {

  public static void main(String[] args) {

    Socket theSocket;
    String hostname;
    DataInputStream theInputStream;
    DataInputStream userInput;
    PrintStream theOutputStream;
    String theLine;

    if (args.length > 0) {
      hostname = args[0];
    }
    else {
      hostname = "localhost";
    }

    try {
      theSocket = new Socket(hostname, 7);
      theInputStream = new DataInputStream(theSocket.getInputStream());
      theOutputStream = new PrintStream(theSocket.getOutputStream());
      userInput = new DataInputStream(System.in);
      while (true) {
        theLine = userInput.readLine();
        if (theLine.equals(".")) break;
        theOutputStream.println(theLine);
        System.out.println(theInputStream.readLine());
      }
    }  // end try
    catch (UnknownHostException e) {
      System.err.println(e);
    }
    catch (IOException e) {
      System.err.println(e);
    }

  }  // end main

}  // end echoClient
