package planetIce;

import java.io.IOException;

import planetIce.network.NetworkClient;

public class PlanetIceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NetworkClient client = new NetworkClient(1234);
		client.sendToServer("Hello");
		try {
			System.out.println( client.readFromServer() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
