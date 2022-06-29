package business.Players.Types;

public class Player {
    protected String name;
    protected int investigationPoints;
    protected String role;

    public Player(String name, int investigationPoints, String role) {
        this.name = name;
        this.investigationPoints = 5;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public int getMaxPoints() {
        return 10;
    }

    public String getNextRole() {
        if (this.role.equals("Engineer")) {
            return "Master";
        } else if (this.role.equals("Master")) {
            return "Doctor";
        } else {
            return null;
        }
    }

    public boolean isDisqualified() {
        return this.investigationPoints <= 0;
    }
}