package presentation.Controllers;

import Persistance.SelectedPersistance;
import business.Editions.EditionManager;
import business.Trials.Trial;
import business.Trials.TrialManager;
import business.Trials.Types.BudgetRequest;
import business.Trials.Types.DoctoralThesisDefense;
import business.Trials.Types.MasterStudies;
import business.Trials.Types.PaperPublication;
import presentation.Menus.MenuComposer;
import presentation.Menus.MenuConductor;
import presentation.Menus.MenuMain;

import java.io.IOException;
import java.util.Objects;

public class ControllerMain {
    private static final String EXIT = "impossibleWord";

    private MenuMain menu;
    private MenuComposer menuComposer;
    private MenuConductor menuConductor;

    private TrialManager trialManager;
    private EditionManager editionManager;

    private SelectedPersistance selectedFaction;

    public ControllerMain() {
        this.menu = new MenuMain();
        this.menuComposer = new MenuComposer();
    }



    public void run() throws IOException {
        String optionFaction;
        do {
            menu.showMainMenu();
            optionFaction = menu.askForString("Pick a faction: ");

            runFaction(optionFaction);
        } while (!Objects.equals(optionFaction, EXIT));
    }



    private void runFaction(String optionFaction) throws IOException {
        String optionRole;
        switch (optionFaction) {
            case "I", "i" -> {
                this.selectedFaction = SelectedPersistance.CSV;
            }
            case "II", "ii" ->{
                this.selectedFaction = SelectedPersistance.JSON;
            }
            default -> menu.showMessage("\nWrong option. Enter 'I' or 'II'");
        }
        this.persistanceToManagers();
        do {
            menu.showMenuRole();
            optionRole = menu.askForString("Enter a role: ");
            runRole(optionRole);
        } while (!Objects.equals(optionRole, EXIT));
    }


    private void persistanceToManagers() {
        this.trialManager = new TrialManager(this.selectedFaction);
        this.editionManager = new EditionManager(this.selectedFaction);
    }


    private void runRole(String optionRole) throws IOException {
        switch (optionRole){
            case "A", "a" -> composerStart();
            case "B", "b" -> conductorStart();
            default -> menu.showMessage("\nWrong option. Enter 'A' or 'B'");
        }
    }



    private void composerStart() throws IOException {
        menu.showMessage("Entering management mode...");
        int option;
        do {
            menuComposer.showMenuComposer();
            option = menu.askForInteger("Enter an option: ");
            runManage(option);
        } while (option != 3);
    }

    private void runManage(int option) throws IOException{
        switch (option) {
            case 1 -> runTrials();
            case 2 -> runEditions();
            case 3 -> menu.showMessage("\nShutting down...");
            default -> menu.showMessage("\nWrong action number, enter a option between 1 and 3, both included");
        }
    }

    private void runTrials() throws IOException {
        String option;
        do {
            menuComposer.showManageTrials();
            option = menu.askForString("Enter an option: ");

            runOptionTrials(option);
        } while (!Objects.equals(option, EXIT));
    }

    private void runOptionTrials(String option) throws IOException {
        switch (option) {
            case "a", "A" -> doCreateTrial();
            case "b", "B" -> doShowListTrials();
            case "c", "C" -> doDeleteTrial();
            case "d", "D" -> composerStart();
            default -> menu.showMessage("\nWrong option. Enter 'A' 'B' 'C' or 'D'");
        }
    }


    private void doCreateTrial(){
        Trial trial = null;
        String trialName, type;
        int typeOp;

        typeOp = menuComposer.askTrialType();

       while(true) {
            trialName = menu.askForString("Enter the trial's name: ");

            if (this.trialManager.isNameUnique(trialName)) {
                menu.showMessage("Trial name must be unique\n");
            } else {
                break;
            }
        }

        switch (typeOp){
            //Paper publication
            case 1 ->{
                int acceptance, rejection, revision, probability = 0;
                String journalName = menu.askForString("Enter the journal's name: ");

                String quartile = menuComposer.askForQuartile();

                do {
                    acceptance = menuComposer.askPercentage("Enter the acceptance probability: ");
                    revision = menuComposer.askPercentage("Enter the revision probability: ");
                    rejection = menuComposer.askPercentage("Enter the rejection probability: ");

                    probability = acceptance + revision + rejection;

                    if (probability != 100){
                        menu.showMessage("Error. The sum must be 100%.");
                    }
                }while (probability != 100);

                trial = new PaperPublication(trialName, journalName, quartile, acceptance, revision, rejection);
            }

            //Master studies
            case 2 ->{
                String masterName = menu.askForString("Enter the master's name: ");

                int ects = menuComposer.askEctsNumber();

                int passProbability = menuComposer.askPassProbability();

                trial = new MasterStudies(trialName, masterName, ects, passProbability);
            }

            //Doctoral thesis defense
            case 3 ->{
                String studyField = menu.askForString("Enter the thesis field of study: ");

                int difficulty = menuComposer.askForDifficulty();

                trial = new DoctoralThesisDefense(trialName, studyField, difficulty);
            }

            //Budget request
            case 4 -> {
                String entityName = menu.askForString("Enter the entity's name: ");

                int budget = this.menuComposer.askForBudget();

                trial = new BudgetRequest(trialName, entityName, budget);
            }

            default -> menu.showMessage("invalid option");
        }

        trialManager.addTrial(trial);
        menu.showMessage("The trial was created successfully!");
    }




    private void doShowListTrials() {
        if (!this.trialManager.getTrials().isEmpty()) {
            int selected;

            while (true){
                menu.showMessage("Here are the current trials, do you want to see more details or go back?\n");
                selected = menuComposer.pickATrial(trialManager.getTrials());

                if (selected < (this.trialManager.getTrials().size())) {
                    trialManager.getTrials().get(selected).showAll();
                }else {
                    break;
                }

            }

        } else {
            this.menu.showMessage("There are no trials");
        }
    }



    private void doDeleteTrial(){
        if (!this.trialManager.getTrials().isEmpty()) {
            int selected;
            while (true){
                menu.showMessage("Here are the current trials, do you want to see more details or go back?\n");
                selected = menuComposer.pickATrial(trialManager.getTrials());

                if (selected < (trialManager.getTrials().size())) {
                    trialManager.getTrials().get(selected).showAll();
                    if (this.trialManager.getTrials().get(selected).getName().equals(menu.askForString("Enter the trial's name for confirmation: "))) {
                        this.trialManager.deleteTrial(selected);

                        menu.showMessage("\nThe trial was successfully deleted.");
                    } else {
                        /* Show error on delete because confirmation was not successfully */
                        menu.spacing();
                        menu.showMessage("Failed to delete Trial, try again");
                    }
                }else {
                    break;
                }
            }

        }else {
            this.menu.showMessage("There are not trials");
        }
    }











    private void runEditions() throws IOException {
        String option;
        do {
            menuComposer.showManageEditions();
            option = menu.askForString("Enter an option: ");

            runOptionEditions(option);
        } while (!Objects.equals(option, EXIT));

    }


    private void runOptionEditions(String option) throws IOException {
        switch (option) {
            case "a", "A" -> doCreateEdition();
            case "b", "B" -> doShowListEditions();
            case "c", "C" -> doDuplicateEdition();
            case "d", "D" -> doDeleteEdition();
            case "e", "E" -> run();
            default -> menu.showMessage("\nWrong option. Enter 'A' 'B' 'C' or 'D'");
        }
    }

    private void doCreateEdition() {

    }

    private void doShowListEditions() {
        if (!this.editionManager.getEditions().isEmpty()) {

        }else {
            this.menu.showMessage("There are not editions");
        }

    }

    private void doDuplicateEdition() {
        if (!this.editionManager.getEditions().isEmpty()) {

        }else {
            this.menu.showMessage("There are not editions");
        }
    }

    private void doDeleteEdition() {
        if (!this.editionManager.getEditions().isEmpty()) {

        }else {
            this.menu.showMessage("There are not editions");
        }
    }

















    private void conductorStart() {
    }

}



