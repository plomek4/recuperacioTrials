package presentation.Controllers;

import Persistance.JsonFileInteract;
import business.Trials.Trial;
import business.Trials.TrialManager;
import business.Trials.Types.BudgetRequest;
import business.Trials.Types.DoctoralThesisDefense;
import business.Trials.Types.MasterStudies;
import business.Trials.Types.PaperPublication;
import presentation.Menus.MenuComposer;

import java.io.IOException;
import java.util.Objects;

public class ControllerComposer {
    private MenuComposer menuComposer;
    private static final String EXIT = "c";
    private TrialManager trialManager = new TrialManager();
    private JsonFileInteract jsonFileInteract = new JsonFileInteract();

    public ControllerComposer(MenuComposer menu) {
        this.menuComposer = menu;
    }

    public void run() throws IOException {
        menuComposer.showMessage("Entering management mode...");
        int option;
        do {
            menuComposer.showMenuComposer();
            option = menuComposer.askForInteger("Enter an option: ");
            runOption(option);
        } while (option != 3);
    }

    private void runOption(int option) throws IOException {
        switch (option) {
            case 1 -> runTrials();
            case 2 -> runEditions();
            case 3 -> menuComposer.showMessage("\nShutting down...");
            default -> menuComposer.showMessage("\nWrong action number, enter a option between 1 and 3, both included");
        }
    }

    public void runTrials() throws IOException {
        String option;
        do {
            menuComposer.showManageTrials();
            option = menuComposer.askForString("Enter an option: ");

            runOptionTrials(option);
        } while (!Objects.equals(option, EXIT));
    }

    private void runOptionTrials(String option) throws IOException {
        switch (option) {
            case "a", "A" -> doCreateTrial();
            case "b", "B" -> menuComposer.showListTrials();//listTrials();
            case "c", "C" -> menuComposer.showDeleteTrial();//deleteTrial();
            case "d", "D" -> run();
            default -> menuComposer.showMessage("\nWrong option. Enter 'A' 'B' 'C' or 'D'");
        }
    }

    public void doCreateTrial() throws IOException {
        int type;
        do {
            menuComposer.showCreateTrial();
            type = menuComposer.askForInteger("Enter the trial’s type: ");
            createTrial(type);
            jsonFileInteract.prova();
        } while (type != 4);
    }

    private void createTrial(int type) {
        Trial trial = null;
        String name = menuComposer.askForString("Enter the trial's name: ");
        switch (type){
            case 1 -> createPaperPublication(name, trial);
            case 2 -> createMasterStudies(name, trial);
            case 3 -> createDoctoralThesisDefense(name, trial);
            case 4 -> createBudgetRequest(name, trial);

            default -> menuComposer.showMessage("\nWrong action number, enter a option between 1 and 4, both included");
        }
    }




    private void createPaperPublication(String name, Trial trial) {
        /* journal's name */
        String journalName = menuComposer.askForString("Enter the journal's name: ");

        /* journal's quartile */
        String quartiles = menuComposer.askForString("Enter the journal’s quartile: ");

        /* acceptance probability */
        int acceptance = menuComposer.askForInteger("Enter the acceptance probability: ");

        /* revision probability */
        int revision = menuComposer.askForInteger("Enter the revision probability: ");

        /* rejection probability */
        int rejection = menuComposer.askForInteger("Enter the acceptance probability: ");

        trial = new PaperPublication(name, journalName, quartiles, acceptance, revision, rejection);
        trialManager.addTrial(trial);
        this.menuComposer.showMessage("The trial was created successfully!\n");
    }


    private void createMasterStudies(String name, Trial trial) {
        /* master's name */
        String masterName = this.menuComposer.askForString("Enter the master's name: ");

        /* number of credits */
        int credits = this.menuComposer.askForInteger("Enter the master's ECTS number: ");

        /* pass probability */
        int passProbability = this.menuComposer.askForInteger("Enter the credit pass probability: ");


        trial = new MasterStudies(name, masterName, credits, passProbability);
        trialManager.addTrial(trial);
        menuComposer.showMessage("The trial was created successfully!\n");
    }



    private void createDoctoralThesisDefense(String name, Trial trial) {
        /* study field */
        String studyField = this.menuComposer.askForString("Enter the thesis field of study: ");

        /* budget amount */
        int difficulty = this.menuComposer.askForInteger("Enter the defense difficulty: ");


        trial = new DoctoralThesisDefense(name, studyField, difficulty);
        trialManager.addTrial(trial);
        this.menuComposer.showMessage("The trial was created successfully!\n");
    }


    private void createBudgetRequest(String name, Trial trial) {
        /* Ask user for the entity's name */
        String entityName = this.menuComposer.askForString("Enter the entity's name: ");

        /* Ask user for the budget amount */
        int budget = this.menuComposer.askForInteger("Enter the budget amount: ");


        trial = new BudgetRequest(name, entityName, budget);
        trialManager.addTrial(trial);
        this.menuComposer.showMessage("The trial was created successfully!\n");
    }



    private void runEditions() {
        menuComposer.showManageEditions();

    }


}

