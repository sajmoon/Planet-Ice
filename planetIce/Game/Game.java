package planetIce.Game;

import java.util.HashMap;
import java.util.HashSet;

import planetIce.Sound.Sound;

public class Game implements Runnable {
    private final boolean DBG = true;
    private static Map theMap;
    private static HashMap<Integer, Player> players;
    private static boolean running = true;
    HashSet<String> commands;

    public Game() {
	theMap = new Map();
	players = new HashMap<Integer, Player>();
	commands();
    }

    /*
     * Do some nice commands? T ex use knife cut dude.. första är ett "action ord. Use, move, etc
     */
    public String act(String[] input, int ID) {
	if (input[0].equals("move")) {
	    // move 1 0
	    // går 1 x 0 y
	    int x = 0, y = 0;

	    try {
		x = Integer.parseInt(input[1]);
		y = Integer.parseInt(input[2]);

	    } catch (Exception e) {
		System.err
			.println("game.Act( move ): Problem conv from string to int. Wrong parameters? input: "
				+ input.toString());

	    }

	    // Check that abs(x) + abs(y) <= 1
	    if (Math.abs(x) + Math.abs(y) <= 1) {
		System.out.println("Client: " + ID + " move " + input[1] + ", "
			+ input[2]);
		move(ID, Integer.parseInt(input[1]), Integer.parseInt(input[2]));
		return "Done";
	    } else {
		return "Failed Error 1"; //Error 1 = för långt steg?
	    }

	}
	return "";
    }

    public void addClient(Player player) {
	players.put(player.ID, player);
	System.out.println("Game() Online players: " + getConnectedPlayers());

    }

    public void clientPinged(int ID) {
	this.getPlayer(ID).setPinged();
    }

    private void commands() {
	commands = new HashSet<String>();
	commands.add("help");
	commands.add("move");
    }

    private int getConnectedPlayers() {
	if (DBG)
	    System.out.println("GameHash: " + this.hashCode());
	return players.size();
    }

    public long getLastPing(int ID) {
	return this.getPlayer(ID).lastPinged();
    }

    private Player getPlayer(int id) {
	return players.get(id);
    }

    public boolean hasCommand(String input) {
	if (input.contains(" ")) {
	    String input2[] = input.split(" ");
	    if (DBG)
		System.out.println("Lenght of split input" + input2.length);
	    if (this.commands.contains(input2[0])) {
		return true;
	    }
	}

	return false;
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

    public boolean isRunning() {
	return running;
    }

    private void move(int playerID, int x, int y) {
	getPlayer(playerID).playerMove(x, y);
    }

    public void printMap() {

	int mapX = theMap.getX();
	int mapY = theMap.getY();

	if (DBG)
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

    public void removeClient(int serverID) {
	players.remove(serverID);
	System.out.println("Game() Online players: " + getConnectedPlayers());

    }

    @Override
    public void run() {
	while (running) {
	    try {
		Thread.sleep(4000);
		///update GUI Map?
		printMap();
		if (DBG)
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

}
