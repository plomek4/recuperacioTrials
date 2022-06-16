package business.Trials.Types;

import business.Trials.Trial;

public class BudgetRequest extends Trial {
    private String entityName;
    private int budgetAmount;


    public BudgetRequest(String name, String entityName, int budgetAmount) {
        super(name);
        this.entityName = entityName;
        this.budgetAmount = budgetAmount;
    }
}
