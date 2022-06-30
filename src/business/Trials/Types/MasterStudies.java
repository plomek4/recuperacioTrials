package business.Trials.Types;

import business.Trials.Trial;

public class MasterStudies extends Trial {
    private String masterName;
    private int creditsQuantity;
    private int approveCreditProbability;

    public MasterStudies(String name, String masterName, int creditsQuantity, int approveCreditProbability) {
        super(name);
        this.masterName = masterName;
        this.creditsQuantity = creditsQuantity;
        this.approveCreditProbability = approveCreditProbability;
    }

    /**
     * Getter of the master's name
     * @return
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Getter of the credits quantity (ects)
     * @return the ects
     */
    public int getCreditsQuantity() {
        return creditsQuantity;
    }

    /**
     * Getter of the approve credit probability
     * @return the approve credit probability
     */
    public int getApproveCreditProbability() {
        return approveCreditProbability;
    }

    /**
     * Function that displays the master's name
     */
    public void showMasterName() {
        System.out.print("Master: " + this.getMasterName() + "\n");
    }

    /**
     * Function that displays the credits quantity (ects)
     */
    public void showMasterECTSNumber() {
        System.out.print("ECTS: " + this.getCreditsQuantity() + ", with a " + this.getApproveCreditProbability()
                + "% chance to pass each one\n");
    }


    /**
     * Function used when the master studies execution start
     * @return 1 or 2
     */
    public int runExecution() {
        int randomNumber = (int) (Math.random() * 100);

        if (randomNumber >= approveCreditProbability) {
            return 1;
        } else {
            return 0;
        }
    }
}
