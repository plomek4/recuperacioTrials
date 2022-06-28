package business.Players.Types;

public class Player {
    protected String name;
    protected int investigationPoints;

    public Player(String name, int investigationPoints) {
        this.name = name;
        this.investigationPoints = investigationPoints;
    }

    public String getName() {
        return name;
    }

    public int getInvestigationPoints() {
        return investigationPoints;
    }

    public void setInvestigationPoints(int investigationPoints) {
        this.investigationPoints = investigationPoints;
    }

    public void addInvestigationPoints(int investigationPoints) {
        this.investigationPoints += investigationPoints;
    }

    public void subtractInvestigationPoints(int investigationPoints) {
        this.investigationPoints -= investigationPoints;
    }
}