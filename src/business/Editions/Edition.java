package business.Editions;

import business.Trials.Trial;

import java.util.List;

public class Edition {
    private int year;
    private int playersQuantity;
    private List<String> trials;

    public Edition(int year, int playersQuantity, int trialsQuantity, List<String> trials) {
        this.year = year;
        this.playersQuantity = playersQuantity;
        this.trials = trials;
    }

    /**
     * Getter of the year of the  year.
     * @return Year of the edition.
     */
    public int getYear() {
        return year;
    }

    /**
     * Function that displays all the information related ro the edition
     */
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

    /**
     * Function that displays the title an edition
     */
    public void showTitle(){
        System.out.print("\nEdition: " + this.getYear() + "\n");
    }

    /**
     * Getter of the number of players
     * @return the number of players
     */
    public int getPlayersQuantity() {
        return playersQuantity;
    }

    /**
     * Getter of the edition's trials
     * @return the trial list
     */
    public List<String> getTrials() {
        return trials;
    }
}
