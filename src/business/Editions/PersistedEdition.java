package business.Editions;

import business.Players.Player;

import java.util.List;


public class PersistedEdition {
    private Edition edition;
    private List<String> trials;
    private List<Player> players;
    private int nextTrialIndex;

    public PersistedEdition(Edition edition, List<String> trials, List<Player> players, int nextTrialIndex) {
        this.edition = edition;
        this.trials = trials;
        this.players = players;
        this.nextTrialIndex = nextTrialIndex;
    }


    /**
     * Getter of the edition
     * @return the edition
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     * Getter of the trials
     * @return the trials
     */
    public List<String> getTrials() {
        return trials;
    }

    /**
     * Getter of the players
     * @return the player list
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Getter of the index that determines the next trial
     * @return the index
     */
    public int getNextTrialIndex() {
        return nextTrialIndex;
    }
}
