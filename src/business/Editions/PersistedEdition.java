package business.Editions;

import business.Players.Types.Player;

import java.util.List;


public class PersistedEdition {
    private Edition edition;
    private List<String> trials;
    private List<Player> players;

    public PersistedEdition(Edition edition, List<String> trials, List<Player> players) {
        this.edition = edition;
        this.trials = trials;
        this.players = players;
    }
}
