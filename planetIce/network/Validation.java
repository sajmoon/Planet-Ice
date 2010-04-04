package planetIce.network;

public class Validation {
    /**
     * Kollar typ checksum. Dock enkelt implementerad. bara längden av strängen.
     * Kanske känns onödigt att ha.
     * @param text Text att kontrollera
     * @return checksum värdet.
     */
    public static String getChecksum(String text) {
	
	return String.valueOf( text.length() );
    }
}
