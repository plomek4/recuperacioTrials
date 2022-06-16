package business.Editions;

import business.Trials.Trial;

import java.util.ArrayList;

public class Edition {
    private int year;
    private int players;
    private int trials;

    private ArrayList<Trial> trialList;


    public Edition() {
        this.year = year;
        this.players = players;
        this.trials = trials;
        this.trialList = new ArrayList<>();
    }

    public ArrayList<Trial> getTrialList() {
        return trialList;
    }

    public void deleteTrial(){

    }
}
