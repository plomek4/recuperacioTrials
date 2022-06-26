package business.Trials;

import Persistance.Fronts.Csv.CsvTrials;
import Persistance.Fronts.Json.JsonTrials;
import Persistance.SelectedPersistance;


import java.util.Comparator;
import java.util.List;


public class TrialManager {
    private List<Trial> trials;

    public TrialManager(SelectedPersistance format) {
        if (format.equals(SelectedPersistance.CSV)){
            CsvTrials csvTrials = new CsvTrials();
            trials = csvTrials.getTrials();
        }else{
            JsonTrials jsonTrials = new JsonTrials();
            trials = jsonTrials.getTrials();
        }
        this.setTrials();
    }

    private void setTrials() {
        this.trials.sort(Comparator.comparing(Trial::getName));
    }

    public void addTrial(Trial trial) {
        this.trials.add(trial);
        setTrials();
    }


    public List<Trial> getTrials() {
        return trials;
    }


    public boolean isNameUnique(String trialName) {
        return this.trials.stream().anyMatch(trial -> trial.getName().equals(trialName));
    }

    public void deleteTrial(int i) {
        trials.remove(i);
        setTrials();
    }
}
