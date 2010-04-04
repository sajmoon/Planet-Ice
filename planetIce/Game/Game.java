package planetIce.Game;

import java.util.HashMap;
import java.util.HashSet;

import planetIce.Sound.Sound;

public class Game implements Runnable {
    private static Map theMap;
    private static HashMap<Integer, Player> players;
    private static boolean running = true;
    HashSet<String> commands;

    public boolean isRunning() {
	return running;
    }

    private boolean isBusy(int x, int y) {
	// System.out.println("Players online " + players.size());
	for (Player tmp : players.values()) {
	    if (tmp.isAt(x, y) == true) {
		return true;
	    }
	}
	return false;
    }

    public void printMap() {
	int mapX = theMap.getX();
	int mapY = theMap.getY();

	System.out.println("0,0 busy? " + isBusy(0, 0) + " "
		+ getConnectedPlayers());
	System.out.println("");
	System.out.println("A map!");
	for (int i = 0; i < mapX; i++) {

	    for (int j = 0; j < mapY; j++) {
		if (isBusy(i, j)) {
		    System.out.print("|X|"); // 3 tecken.
		} else {
		    System.out.print("| |"); // 3 tecken.
		}

	    }
	    System.out.println();
	}
    }

    public void clientPinged(int ID) {
	this.getPlayer(ID).setPinged();
    }

    public long getLastPing(int ID) {
	return this.getPlayer(ID).lastPinged();
    }

    public boolean hasCommand(String input) {
	if ( input.contains(" ") ) {
	    String input2[] = input.split(" ");
	    if (this.commands.contains(input2[0])) {
		return true;    
	    }   
	}
	
	return false;
    }

    private void commands() {
	commands.add("help");
	commands.add("move");
    }

    public Game() {
	theMap = new Map();
	players = new HashMap<Integer, Player>();

    }

    @Override
    public void run() {
	while (running) {
	    try {
		Thread.sleep(4000);
		printMap();
		System.out.println("GameHash: " + this.hashCode());
		// System.out.println("500 more!");
		// här rör vi alla object, t ex om vi ska ha motståndarde så kan de röras här. dvs efter varje sleep så
		// gör dom sitt move.
		// Sound.playScream(); //kanske dumt att ha ljud på servern. men men ;D
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.err.println("Game().run() " + e.toString());
		e.printStackTrace();
	    }

	}
    }

    /*
     * Do some nice commands? T ex use knife cut dude.. första är ett "action ord. Use, move, etc
     */
    public void act(String[] input, int ID) {
	if (input[0].equals("move")) {
	    // move 1 2
	    // går 1 x 2 y

	    // TODO kräver korrekt data..
	    System.out.println("Client: " + ID + " move " + input[1] + ", "
		    + input[2]);
	    move(ID, Integer.parseInt(input[1]), Integer.parseInt(input[2]));

	}
    }

    public void addClient(Player player) {
	players.put(player.ID, player);
	System.out.println("Game() Online players: " + getConnectedPlayers());

    }

    private int getConnectedPlayers() {
	System.out.println("GameHash: " + this.hashCode());
	return players.size();
    }

    public void removeClient(int serverID) {
	players.remove(serverID);
	System.out.println("Game() Online players: " + getConnectedPlayers());

    }

    private void move(int playerID, int x, int y) {
	getPlayer(playerID).playerMove(x, y);
    }

    private Player getPlayer(int id) {
	return players.get(id);
    }

}
