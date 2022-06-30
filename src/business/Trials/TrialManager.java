package business.Trials;

import Persistance.Fronts.Csv.CsvTrials;
import Persistance.Fronts.Json.JsonTrials;
import Persistance.SelectedPersistance;
import business.Trials.Types.*;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TrialManager {
    private List<Trial> trials;
    private SelectedPersistance persistanceFormat;

    public TrialManager(SelectedPersistance format) {
        if (format.equals(SelectedPersistance.CSV)){
            persistanceFormat = SelectedPersistance.CSV;
            CsvTrials csvTrials = new CsvTrials();
            trials = csvTrials.getTrials();
        }else{
            persistanceFormat = SelectedPersistance.JSON;
            JsonTrials jsonTrials = new JsonTrials();
            trials = jsonTrials.getTrials();
        }
        this.setTrials();
    }

    /**
     * Setter of the trials
     */
    private void setTrials() {
        this.trials.sort(Comparator.comparing(Trial::getName));
    }

    /**
     * Function that adds a trial to the trial list
     * @param trial the trial to add
     */
    public void addTrial(Trial trial) {
        this.trials.add(trial);
        setTrials();
    }


    /**
     * Function that calls the persistance to write the trials depending on it's type in the correct file
     */
    public void writeTrials(){
        for (Types types : Types.values()) {
            List<Trial> typeOfTrials = new LinkedList<>();

            for (Trial trial : trials) {
                switch (types) {
                    case paper_publication -> {
                        if (trial instanceof PaperPublication) {
                            typeOfTrials.add(trial);
                        }
                    }
                    case master_studies -> {
                        if (trial instanceof MasterStudies) {
                            typeOfTrials.add(trial);
                        }
                    }
                    case doctoral_thesis -> {
                        if (trial instanceof DoctoralThesis) {
                            typeOfTrials.add(trial);
                        }
                    }
                    case budget_request -> {
                        if (trial instanceof BudgetRequest) {
                            typeOfTrials.add(trial);
                        }
                    }
                }
            }
            if (persistanceFormat.equals(SelectedPersistance.CSV)) {
                new CsvTrials().writeTrials(typeOfTrials, types);
            } else {
                new JsonTrials().writeTrials(typeOfTrials, types);
            }
        }
    }

    /**
     * Getter of the trial list
     * @return the trial list
     */
    public List<Trial> getTrials() {
        return trials;
    }

    /**
     * Function that gets a trial by its name
     * @param name the trial name
     * @return the trial
     */
    public Trial getTrialByName(String name) {
        for (Trial trial : trials) {
            if (trial.getName().equals(name)) {
                return trial;
            }
        }
        return null;
    }

    /**
     * Function that gets a trial's type by its name
     * @param name the type's name
     * @return the trial's type
     */
    public Types getTrialTypeByName(String name) {
        Trial trial = getTrialByName(name);
        return trial.getTrialType(trial);
    }

    /**
     * Function that checks if a trial name is unique
     * @param trialName the name of the trial to check
     * @return true or false depending on the result
     */
    public boolean isNameUnique(String trialName) {
        return this.trials.stream().anyMatch(trial -> trial.getName().equals(trialName));
    }

    /**
     * Function that delets a trial from the list
     * @param i the indexx to determine which trial
     */
    public void deleteTrial(int i) {
        trials.remove(i);
        setTrials();
    }
}
