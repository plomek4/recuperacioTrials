package business.Trials.Types;

import business.Trials.Trial;

public class PaperPublication extends Trial {
    private String journalName;
    private String quartile;
    private int acceptationProbability;
    private int revisionProbability;
    private int refuseProbability;

    public PaperPublication(String name, String journalName, String quartile, int acceptationProbability,
                            int revisionProbability, int refuseProbability) {
        super(name);
        this.journalName = journalName;
        this.quartile = quartile;
        this.acceptationProbability = acceptationProbability;
        this.revisionProbability = revisionProbability;
        this.refuseProbability = refuseProbability;
    }

    /**
     * Getter of the journal name
     * @return the journal name
     */
    public String getJournalName() {
        return journalName;
    }

    /**
     * Getter of the acceptance probability
     * @return the acceptance probability
     */
    public int getAcceptationProbability() {
        return acceptationProbability;
    }

    /**
     * Getter of the revision probability
     * @return the revision probability
     */
    public int getRevisionProbability() {
        return revisionProbability;
    }

    /**
     * Getter of the refuse probability
     * @return the refuse probability
     */
    public int getRefuseProbability() {
        return refuseProbability;
    }

    /**
     * Getter of the quartile
     * @return the quartile
     */
    public String getQuartile(){return quartile;}

    /**
     * Function that displays the journal name
     */
    public void showJournalName() {
        System.out.print("Journal: " + this.getJournalName() + "\n");
    }

    /**
     * Function that displays the probabilities all in one
     */
    public void showProbabilities() {
        System.out.print("Chances: " + this.getAcceptationProbability() + "% acceptance, "
                + this.getRevisionProbability() + "% revision, " + this.getRefuseProbability() + "% rejection\n");
    }

    /**
     * Function used when the paper publication execution start
     * @return 1, 2 or 3
     */
    public int startTrial() {
        int randomNum  = (int)(Math.random()*100+1);

        if (randomNum <= acceptationProbability){
            return 1;
        }else if (randomNum <= (acceptationProbability + revisionProbability)){
            return 2;
        }
        else{
            return 3;
        }
    }
}
