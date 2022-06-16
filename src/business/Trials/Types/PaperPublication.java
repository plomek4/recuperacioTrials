package business.Trials.Types;

import business.Trials.Trial;

public class PaperPublication extends Trial {
    private String journalName;
    private String journalQuartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;

    public PaperPublication(String name, String journalName, String journalQuartile, int acceptanceProbability,
                            int revisionProbability, int rejectionProbability) {
        super(name);
        this.journalName = journalName;
        this.journalQuartile = journalQuartile;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;
    }



    public void setJournalName(String journalName) {this.journalName = journalName;}
    public void setJournalQuartile(String journalQuartile) {this.journalQuartile = journalQuartile;}
    public void setAcceptanceProbability(int acceptanceProbability) {this.acceptanceProbability = acceptanceProbability;}
    public void setRevisionProbability(int revisionProbability) {this.revisionProbability = revisionProbability;}
    public void setRejectionProbability(int rejectionProbability) {this.rejectionProbability = rejectionProbability;}
}
