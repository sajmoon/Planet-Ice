package planetIce;

import planetIce.network.NetworkServer;

public class PlanetIceServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PlanetIceServer");
		System.out.println("Starting..");
		try {
			new NetworkServer().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done..");

	}

}
