package planetIce.network.old;

public class nServer {

    public static void main(String[] args) {
	System.out.println("Start");
	mRecive r = new mRecive();

	r.start();
	
	System.out.println("End");
    }
}
