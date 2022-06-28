package business.Trials;

import Persistance.Fronts.Csv.CsvTrials;
import Persistance.Fronts.Json.JsonEditions;
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

    private void setTrials() {
        this.trials.sort(Comparator.comparing(Trial::getName));
    }

    public void addTrial(Trial trial) {
        this.trials.add(trial);
        setTrials();
    }


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
