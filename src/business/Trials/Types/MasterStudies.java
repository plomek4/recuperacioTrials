package business.Trials.Types;

import business.Trials.Trial;

public class MasterStudies extends Trial {
    private String masterName;
    private int masterECTSNumber;
    private int creditPassProbability;


    public MasterStudies(String name, String masterName, int masterECTSNumber, int creditPassProbability) {
        super(name);
        this.masterName = masterName;
        this.masterECTSNumber = masterECTSNumber;
        this.creditPassProbability = creditPassProbability;
    }
}
