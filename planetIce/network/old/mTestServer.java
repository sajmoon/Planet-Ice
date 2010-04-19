package planetIce.network.old;


public class mTestServer {

    public static void main(String[] args) {
	System.out.println("Start");
	mRecive r = new mRecive();
	System.out.println(".start..");
	r.start();
	
	System.out.println("End");
    }
}
