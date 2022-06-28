package business.Editions;

import business.Trials.Trial;

import java.util.List;

public class Edition {
    private int year;
    private int playersQuantity;
    private int trialsQuantity;
    private List<String> trials;

    public Edition(int year, int playersQuantity, int trialsQuantity, List<String> trials) {
        this.year = year;
        this.playersQuantity = playersQuantity;
        this.trialsQuantity = trialsQuantity;
        this.trials = trials;
    }

    public int getYear() {
        return year;
    }

    public void showAll() {
        int i = 1;

        System.out.print("\nTrial: " + this.getYear() + "\n");
        System.out.println("Players: " + this.getPlayersQuantity());
        System.out.println("Trials: ");

        for (String trial : this.getTrials()) {
            System.out.println("\t" + i + "- " + trial);
            i++;
        }
    }

    public void showTitle(){
        System.out.print("\nEdition: " + this.getYear() + "\n");
    }

    public int getPlayersQuantity() {
        return playersQuantity;
    }

    public int getTrialsQuantity() {
        return trialsQuantity;
    }

    public List<String> getTrials() {
        return trials;
    }
}
