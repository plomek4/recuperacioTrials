package business.Players;

public class Player{
    protected String name;
    protected int investigationPoints;
    protected String role;

    public Player(String name, int investigationPoints, String role) {
        this.name = name;
        this.investigationPoints = 5;
        this.role = role;
    }

    /**
     * Getter of the player's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the player's role
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter of the role
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter of the Investigation Points (PI) of a player
     * @return Number of PI
     */
    public int getInvestigationPoints() {
        return investigationPoints;
    }

    /**
     * Setter of the investigation points
     * @param investigationPoints the number of investigation points
     */
    public void setInvestigationPoints(int investigationPoints) {
        this.investigationPoints = investigationPoints;
    }

    /**
     * Function that adds investigation points to a player
     * @param investigationPoints Number of investigation points
     */
    public void addInvestigationPoints(int investigationPoints) {
        this.investigationPoints += investigationPoints;
    }

    /**
     * Function that substract investigation points from a player
     * @param investigationPoints Number of investigation points
     */
    public void subtractInvestigationPoints(int investigationPoints) {
        this.investigationPoints -= investigationPoints;
    }

    /**
     *  Function that gets the possible max points (10)
     * @return the possible max points (10)
     */
    public int getMaxPoints() {
        return 10;
    }

    /**
     * Function that get the next role
     * @return the role
     */
    public String getNextRole() {
        if (this.role.equals("Engineer")) {
            return "Master";
        } else if (this.role.equals("Master")) {
            return "Doctor";
        } else {
            return "Doctor";
        }
    }

    /**
     * Function that checks if a player is disqualified
     * @return true if the investigation points are below 0
     */
    public boolean isDisqualified() {
        return this.investigationPoints <= 0;
    }
}