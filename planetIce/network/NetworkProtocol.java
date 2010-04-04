package planetIce.network;

import planetIce.Game.Player;

public interface NetworkProtocol {
    public String act(String input, int serverID);

    public void joinGame(Player player);

    public void removeFromGame(int serverID);
}
