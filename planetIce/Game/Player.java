package planetIce.Game;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class Player {
    String name;
    int ID;
    private int xPos;
    private int yPos;
    private long lastPing;
    
    public boolean isAt(int x, int y) {
	if (xPos == x && yPos == y) {
	    return true;
	} else { 
	    return false;
	}
    }
    public Player(String inputName, int inputID) {
	name = inputName;
	ID = inputID;
	this.xPos = 0;
	this.yPos = 0;
	lastPing = 0;
    }

    public long lastPinged() {
	return planetIce.utils.Utils.getTime() - lastPing; 
    }
    
    public void setPinged() {
	lastPing = planetIce.utils.Utils.getTime();
    }
    
    public void playerMove(int x, int y) {
	xPos += x;
	yPos += y;
    }
    
    public int getX() {
	return xPos;
    }
    
    public int getY() {
	return yPos;
    }
    
    public int getID() {
	return ID;
    }
}
