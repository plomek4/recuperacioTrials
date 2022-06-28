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

    public String getJournalName() {
        return journalName;
    }

    public int getAcceptationProbability() {
        return acceptationProbability;
    }

    public int getRevisionProbability() {
        return revisionProbability;
    }

    public int getRefuseProbability() {
        return refuseProbability;
    }

    public String getQuartile(){return quartile;}

    public void showJournalName() {
        System.out.print("Journal: " + this.getJournalName() + "\n");
    }

    public void showProbabilities() {
        System.out.print("Chances: " + this.getAcceptationProbability() + "% acceptance, "
                + this.getRevisionProbability() + "% revision, " + this.getRefuseProbability() + "% rejection\n");
    }

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
