package planetIce.network.old;

import sun.net.*;

import java.io.IOException;
import java.net.*;

public class mRecive {

    public void start() {

	try {
	    // 1.

	    // Import some needed classes

	    // 2.

	    // Which port should we listen to
	    int port = 5000;
	    // Which address
	    String group = "225.4.5.6";

	    // 3.

	    // Create the socket and bind it to port 'port'.
	    MulticastSocket s = new MulticastSocket(port);

	    // 4.

	    // join the multicast group
	    s.joinGroup(InetAddress.getByName(group));
	    // Now the socket is set up and we are ready to receive packets

	    // 5.

	    // Create a DatagramPacket and do a receive
	    byte buf[] = new byte[1024];
	    DatagramPacket pack = new DatagramPacket(buf, buf.length);
	    s.receive(pack);

	    // 6.

	    // Finally, let us do something useful with the data we just received,
	    // like print it on stdout :-)
	    System.out.println("Received data from: "
		    + pack.getAddress().toString() + ":" + pack.getPort()
		    + " with length: " + pack.getLength());
	    System.out.write(pack.getData(), 0, pack.getLength());
	    System.out.println();

	    // 7.

	    // And when we have finished receiving data leave the multicast group and
	    // close the socket
	    s.leaveGroup(InetAddress.getByName(group));
	    s.close();
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    System.out.println("Error1");
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    System.out.println("Error2");
	    e.printStackTrace();
	}
    }
}