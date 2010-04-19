package planetIce.network.old;

import planetIce.Game.Game;
import planetIce.Game.Player;

public class PlanetIceServerProtocol implements NetworkProtocol {
    Game game;
    public PlanetIceServerProtocol( Game inputGame ) {
	game = inputGame;
    }
    
    @Override
    public String act(String input, int ID) {
	try {
	    // TODO Auto-generated method stub
	    if (input == null) {
	        // klienten kopplade ifrån.
	        return null;
	    } else if (input.equals("Ping?")) {
	        // För att testa connections. Typ ;) Och den är jävligt söt att ha.
		//System.out.println("Lastpinged: " + game.getLastPing(ID));
		game.clientPinged(ID);
	        return "Pong!";
	    } else if (game.hasCommand( input )) {
	        //mitt förslag är att vi kör det enkelt. 
	        //T ex "move 1 2". left x y
	        //eller "use knife attack dude1"
		//eller så kräver vi att de skickar med id numret först dvs
		
	        game.act(input.split(" "), ID);
	    } else if(input.equals("help")) {
	        return "You dont need no help biaatch..";  
	    } else {
	        return "Mirror: (" + input + ")";
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    System.err.println("Protocol. " + e.toString());
	    e.printStackTrace();
	}
	return null;
    }

    public void setGame(Game g) {
	// TODO Auto-generated method stub
	game = g;
    }

    public void joinGame(Player player) {
	// TODO Auto-generated method stub
	game.addClient(player);
	System.out.println( player.getID() + " is at 0,0? " + player.isAt(0, 0) );
    }

    @Override
    public void removeFromGame(int serverID) {
	game.removeClient( serverID );
    }
    
    
}
