package business.Editions;

import business.Players.Engineer;

import java.util.List;

public class AvailableEditions {
    private Edition edition;
    private List<Engineer> players;
    private int lastTrialIndex;

    public AvailableEditions(Edition edition, List<Engineer> players, int lastTrialIndex) {
        this.edition = edition;
        this.players = players;
        this.lastTrialIndex = lastTrialIndex;
    }


    public Edition getEdition() {
        return edition;
    }

    public List<Engineer> getPlayers() {
        return players;
    }
}
