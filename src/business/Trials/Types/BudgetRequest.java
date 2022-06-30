package business.Trials.Types;

import business.Players.Player;
import business.Trials.Trial;

public class BudgetRequest extends Trial {
    private String entityName;
    private int budgetQuantity;

    public BudgetRequest(String name, String entityName, int budgetQuantity) {
        super(name);
        this.entityName = entityName;
        this.budgetQuantity = budgetQuantity;
    }

    /**
     * Getter of the budget quantity
     * @return the budget quantity
     */
    public int getBudgetQuantity() {
        return budgetQuantity;
    }

    /**
     * Getter of the entity name
     * @return the entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Function that displays the entity name
     */
    public void showEntityName() {
        System.out.print("Entity: " + this.getEntityName() + "\n");
    }

    /**
     * Function that displays the budget amount
     */
    public void showBudgetAmount() {
        System.out.print("Budget: " + this.getBudgetQuantity() + " €\n");
    }

    /**
     * Function used when the budget request trial starts
     * @return the solicited float casted to an integer
     */
    public int startTrial() {
        return (int) (Math.log(getBudgetQuantity()) / Math.log(2));
    }

    /**
     * Function that calcultaes the reward for a player
     * @param p the player
     * @return the reward
     */
    public float IPReward(Player p) {
        return (float) Math.ceil(p.getInvestigationPoints() / 2);
    }
}
