package business.Trials;

import business.Trials.Types.*;

import java.lang.reflect.Type;

public class Trial {
    private String name;

    public Trial(String name) {
        this.name = name;
    }

    /**
     * Getter of the trial's name
     * @return the trial's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the trial's name
     * @param trialName the name of the trial
     */
    public void setName(String trialName) {this.name = trialName;}

    /**
     * Function that displays the Trials title
     */
    public void showAll() {
        System.out.print("\nTrial: " + this.getName() + "\n");
    }

    /**
     * Function that gets which type of trial is it
     * @param trial the trial in question
     * @return the trial's type
     */
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