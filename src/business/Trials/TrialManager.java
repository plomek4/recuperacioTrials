package business.Trials;

import Persistance.JsonFileInteract;
import business.Trials.*;
import business.Trials.Types.BudgetRequest;
import business.Trials.Types.DoctoralThesisDefense;
import business.Trials.Types.MasterStudies;
import business.Trials.Types.PaperPublication;
import presentation.Menus.MenuMain;

import java.util.List;


public class TrialManager {
    private JsonFileInteract jsonFileInteract;
    private List<Trial> trials;


    public void addTrial(Trial trial) {
        this.trials.add(trial);
    }


    public List<Trial> getTrials() {
        return trials;
    }



}
