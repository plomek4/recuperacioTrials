package business.Players;

public class Engineer {
    protected String name;
    protected int investigationPoints;
    protected  String rank;

    public Engineer(String name) {
        this.name = name;
        this.investigationPoints = 5;
        this.rank = this.getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public int getInvestigationPoints() {
        return investigationPoints;
    }

    public String getRank() {
        return rank;
    }

}
