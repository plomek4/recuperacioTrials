package business.Players;

import business.Players.Types.Player;

import java.util.List;

public class PlayerManager {
    private List<Player> players;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean arePlayersStillInGame() {
        for(Player player : this.players) {
            if(player.getInvestigationPoints() > 0) {
                return true;
            }
        }
        return false;
    }
}
