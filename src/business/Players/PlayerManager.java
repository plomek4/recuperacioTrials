package business.Players;

import java.util.List;

public class PlayerManager {
    private List<Player> players;

    /**
     * Setter of the players
     * @param players the list of players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Getter of the players
     * @return the player list
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Function that checks if a player is still playing
     * @return true or false depending on the status of the player
     */
    public boolean arePlayersStillInGame() {
        for(Player player : players) {
            if(player.getInvestigationPoints() > 0) {
                return true;
            }
        }
        return false;
    }
}
