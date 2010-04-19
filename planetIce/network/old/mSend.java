package planetIce.network.old;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class mSend {
 //   1.
    public void start() {
	
    
    
    try {
	// Import some needed classes
   
// 2.

	// Which port should we send to
	int port = 5000;
	// Which address
	String group = "225.4.5.6";
	// Which ttl
	int ttl = 1;

// 3.

	// Create the socket but we don't bind it as we are only going to send data
	MulticastSocket s = new MulticastSocket();

// 4.

	// Note that we don't have to join the multicast group if we are only
	// sending data and not receiving

// 5.

	// Fill the buffer with some data
	byte buf[] = new byte[10];
	for (int i=0; i<buf.length; i++) buf[i] = (byte)i;
	// Create a DatagramPacket 
	DatagramPacket pack = new DatagramPacket(buf, buf.length,
						 InetAddress.getByName(group), port);
	// Do a send. Note that send takes a byte for the ttl and not an int.
	s.send(pack,(byte)ttl);

// 6.

	// And when we have finished sending data close the socket
	s.close();
    } catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
    
    }

}
