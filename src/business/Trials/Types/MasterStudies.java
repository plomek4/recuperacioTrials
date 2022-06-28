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

    public String getMasterName() {
        return masterName;
    }

    public int getCreditsQuantity() {
        return creditsQuantity;
    }

    public int getApproveCreditProbability() {
        return approveCreditProbability;
    }

    public void showMasterName() {
        System.out.print("Master: " + this.getMasterName() + "\n");
    }

    public void showMasterECTSNumber() {
        System.out.print("ECTS: " + this.getCreditsQuantity() + ", with a " + this.getApproveCreditProbability()
                + "% chance to pass each one\n");
    }

    public int runExecution() {
        int randomNumber = (int) (Math.random() * 100);

        if (randomNumber >= approveCreditProbability) {
            return 1;
        } else {
            return 0;
        }
    }
}
