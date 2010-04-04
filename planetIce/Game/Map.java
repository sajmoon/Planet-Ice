package planetIce.Game;

import java.util.Random;

public class Map {
    int x = 10;
    


    int y = 10;
    int[][] map = new int[x][y];
    
    
    public Map() {
	// 0 vanligt
	// 1 annan
	// 2 omöjlig
	Random r = new Random();
	for (int i = 0; i < 10; i++) {
	    for (int u = 0; u < 10; u++) {
		map[i][u] = r.nextInt(3);
	    }
	}
	map[0][0] = 0; //Vi börjar här.
    }
        
    public int getTypeOfLand(int x, int y) {
	return map[x][y];
    }
    
    public boolean isPossible(int direction) {
	return true;
    }
    
    public int getX() {
        return x;
    }

    
    public int getY() {
        return y;
    }

}
