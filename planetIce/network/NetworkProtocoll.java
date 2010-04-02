package planetIce.network;

public class NetworkProtocoll  {
	 	
    /* (non-Javadoc)
	 * @see planetIce.network.NetworkProtocollInterface#processInput(java.lang.String)
	 */
    public String processInput(String theInput) {
    	if (theInput.equals("Connection test")) {
    		return "OK";
    	}
    	return theInput;
    }
}
