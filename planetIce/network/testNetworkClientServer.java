package planetIce.network;

import junit.framework.TestCase;

public class testNetworkClientServer extends TestCase {
    
   
    public void test1() {
	//Thread thread;
	NetworkThread nt1 = new NetworkThread();
	Thread t1 = new Thread(nt1);
	t1.start();
	
	NetworkThread nt2 = new NetworkThread();
	Thread t2 = new Thread(nt2);
	t2.start();
	
	
	nt1.send("Test");
	nt2.send("Test2");
	
	System.out.println( nt2.getText() );
	//assertTrue("hello".equals("hello"));
	
    }
}
