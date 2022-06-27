package business.Trials.Types;

import business.Trials.Trial;

public class BudgetRequest extends Trial {
    private String entityName;
    private int budgetQuantity;


    public BudgetRequest(String name, String entityName, int budgetQuantity) {
        super(name);
        this.entityName = entityName;
        this.budgetQuantity = budgetQuantity;
    }

    public int getBudgetQuantity() {
        return budgetQuantity;
    }

    public String getEntityName() {
        return entityName;
    }

    public void showEntityName() {
        System.out.print("Entity: " + this.getEntityName() + "\n");
    }

    public void showBudgetAmount() {
        System.out.print("Budget: " + this.getBudgetQuantity() + " €\n");
    }
}