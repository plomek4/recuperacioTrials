package business.Trials;

import business.Trials.Types.*;

import java.lang.reflect.Type;

public class Trial {
    private String name;

    public Trial(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String trialName) {this.name = trialName;}



    public void showAll() {
        System.out.print("\nTrial: " + this.getName() + "\n");
    }

    // get trial type instance of classes
    public Types getTrialType(Trial trial) {

        if (trial instanceof BudgetRequest) {
            return Types.budget_request;
        } else if (trial instanceof MasterStudies) {
            return Types.master_studies;
        } else if (trial instanceof DoctoralThesis) {
            return Types.doctoral_thesis;
        } else if (trial instanceof PaperPublication) {
            return Types.paper_publication;
        } else {
            return null;
        }

    }

}