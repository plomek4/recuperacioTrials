package business.Editions;

import business.Editions.Edition;
import business.Trials.Trial;

public class EditionManager {
    private Edition edition;

    public void addTrial(Trial trial){
        edition.getTrialList().add(trial);
    }

}
