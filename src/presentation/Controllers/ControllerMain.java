package presentation.Controllers;

import Persistance.SelectedPersistance;
import business.Editions.Edition;
import business.Editions.EditionManager;
import business.Players.PlayerManager;
import business.Trials.Trial;
import business.Trials.TrialManager;
import business.Trials.Types.BudgetRequest;
import business.Trials.Types.DoctoralThesis;
import business.Trials.Types.MasterStudies;
import business.Trials.Types.PaperPublication;
import presentation.Menus.MenuComposer;
import presentation.Menus.MenuConductor;
import presentation.Menus.MenuMain;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerMain {
    private static final String EXIT = "impossibleWord";

    private MenuMain menu;
    private MenuComposer menuComposer;
    private MenuConductor menuConductor;

    private TrialManager trialManager;
    private EditionManager editionManager;
    private PlayerManager playerManager;

    private SelectedPersistance selectedFaction;

    public ControllerMain() {
        this.menu = new MenuMain();
        this.menuComposer = new MenuComposer();
        this.menuConductor = new MenuConductor();
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

    private void persistanceToManagers() throws IOException {
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
        String optionRole;
        menu.showMessage("Entering management mode...");
        int option;
        do {
            menuComposer.showMenuComposer();
            option = menu.askForInteger("Enter an option: ");
            runManage(option);
        } while (option != 3);
        do {
            menu.showMenuRole();
            optionRole = menu.askForString("Enter a role: ");
            runRole(optionRole);
        } while (!Objects.equals(optionRole, EXIT));
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
            case "d", "D" -> {
                trialManager.writeTrials();
                composerStart();
            }
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

                trial = new DoctoralThesis(trialName, studyField, difficulty);
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
                menu.showMessage("\nHere are the current trials, do you want to see more details or go back?\n");
                selected = menuComposer.pickATrial(trialManager.getTrials()) - 1;

                if (selected < (this.trialManager.getTrials().size())) {
                    trialManager.getTrials().get(selected).showAll();

                    if (trialManager.getTrials().get(selected) instanceof BudgetRequest) {
                        ((BudgetRequest) trialManager.getTrials().get(selected)).showEntityName();
                        ((BudgetRequest) trialManager.getTrials().get(selected)).showBudgetAmount();
                    } else if (trialManager.getTrials().get(selected) instanceof DoctoralThesis) {
                        ((DoctoralThesis) trialManager.getTrials().get(selected)).showThesisFieldOfStudy();
                        ((DoctoralThesis) trialManager.getTrials().get(selected)).showDefenseDifficulty();
                    } else if (trialManager.getTrials().get(selected) instanceof MasterStudies) {
                        ((MasterStudies) trialManager.getTrials().get(selected)).showMasterName();
                        ((MasterStudies) trialManager.getTrials().get(selected)).showMasterECTSNumber();
                    } else {
                        ((PaperPublication) trialManager.getTrials().get(selected)).showJournalName();
                        ((PaperPublication) trialManager.getTrials().get(selected)).showProbabilities();
                    }

                } else if (selected == (this.trialManager.getTrials().size())) {
                    break;
                } else {
                    System.out.println("\nWrong option. Enter a valid option");
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
                selected = menuComposer.pickATrial(trialManager.getTrials()) - 1;

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
        } else {
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
            case "e", "E" -> {
                editionManager.writeEditions();
                composerStart();
            }
            default -> menu.showMessage("\nWrong option. Enter 'A' 'B' 'C' 'D' or 'E'");
        }
    }

    private void doCreateEdition() {
        Edition edition = null;
        int editionYear;
        int initialNumberOfPlayers;
        int numberOfTrials;
        int trialsPicked = 0;
        int trialsPickedCounter = 0;
        List<String> pickedTrialsList = new LinkedList<>();

        while(true) {
            editionYear = menu.askForInteger("Enter the edition’s year: ");
            if (this.editionManager.isYearUnique(editionYear)
                    || editionYear < Calendar.getInstance().get(Calendar.YEAR)) {
                menu.showMessage("Edition year must be unique and higher than current\n");
            } else {
                break;
            }
        }

        while(true) {
            initialNumberOfPlayers = menu.askForInteger("Enter the initial number of players: ");
            if (initialNumberOfPlayers < 1 || initialNumberOfPlayers > 5) {
                menu.showMessage("Initial number of players must be between 1 and 5\n");
            } else {
                break;
            }
        }

        while(true) {
            numberOfTrials = menu.askForInteger("Enter the number of trials: ");
            if (numberOfTrials < 3 || numberOfTrials > 12) {
                menu.showMessage("The number of trials must be between 3 and 12\n");
            } else {
                break;
            }
        }

        menu.showMessage("\n\t --- Trials ---\n");
        for (int i = 0; i < this.trialManager.getTrials().size(); i++){
            menu.showMessage("\t"+ (i + 1) + ") " + this.trialManager.getTrials().get(i).getName());
        }
        menu.showMessage("");

        do {
            int selectedTrial = menu.askForInteger("Pick a trial (" + (trialsPickedCounter + 1) + "/" + numberOfTrials + "): ");
            selectedTrial = selectedTrial - 1;
            if (selectedTrial > - 1 && selectedTrial <= this.trialManager.getTrials().size() - 1) {
                trialsPicked++;
                pickedTrialsList.add(this.trialManager.getTrials().get(selectedTrial).getName());
            } else {
                menu.showMessage("\nWrong option. Enter a valid option");
                trialsPickedCounter--;
            }
            trialsPickedCounter++;
            System.out.println("trials number:" + numberOfTrials + ", trials picked:" + trialsPicked);
        } while (trialsPicked < numberOfTrials);

        editionManager.addEdition(new Edition(editionYear, initialNumberOfPlayers, numberOfTrials, pickedTrialsList));
        menu.showMessage("\nThe edition was created successfully!");
    }

    private void doShowListEditions() {
        if (!this.editionManager.getEditions().isEmpty()) {
            int selected;

            while (true) {
                menu.showMessage("\nHere are the current editions, do you want to see more details or go back?\n");
                selected = menuComposer.pickAnEdition(editionManager.getEditions()) - 1;

                if (selected < (this.editionManager.getEditions().size())) {
                    editionManager.getEditions().get(selected).showAll();


                } else if (selected == (this.editionManager.getEditions().size())) {
                    break;
                } else {
                    System.out.println("\nWrong option. Enter a valid option");
                }
            }

        } else {
            this.menu.showMessage("There are not editions");
        }

    }

    private void doDuplicateEdition() {
        int selected, playersNum, year;
        Edition edition, e;
        if (!this.editionManager.getEditions().isEmpty()) {
            while (true) {
                menu.showMessage("Which edition do you want to clone?\n");
                selected = menuComposer.pickAnEdition(editionManager.getEditions()) - 1;

                if (selected == (editionManager.getEditions().size())){
                    break;
                }

                if (selected < (editionManager.getEditions().size())) {

                    edition = editionManager.getTrialsFromEdition(selected);

                    while (true) {
                        year = menu.askForInteger("Enter the edition’s year: ");
                        if (this.editionManager.isYearUnique(year)
                                || year < Calendar.getInstance().get(Calendar.YEAR)) {
                            menu.showMessage("Edition year must be unique and higher than current\n");
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        playersNum = menu.askForInteger("Enter the initial number of players: ");
                        if (playersNum < 1 || playersNum > 5) {
                            menu.showMessage("Initial number of players must be between 1 and 5\n");
                        } else {
                            break;
                        }
                    }

                    e = new Edition(year, playersNum, edition.getTrials().size(), edition.getTrials());
                    editionManager.addEdition(e);
                    menu.showMessage("\nThe edition was cloned successfully!");
                    break;
                }else {
                    menu.showMessage("Enter a valid option\n");
                }
            }
        }else {
            this.menu.showMessage("There are not editions");
        }
    }



    private void doDeleteEdition() {
        if (!this.editionManager.getEditions().isEmpty()) {
            int selected, confirmation;
            while (true){
                menu.showMessage("Which edition do you want to delete?\n");
                selected = menuComposer.pickAnEdition(editionManager.getEditions()) - 1;

                if (selected < (editionManager.getEditions().size())) {
                    editionManager.getEditions().get(selected).showTitle();
                    confirmation = menu.askForInteger("Enter the edition’s year for confirmation:");
                    if (editionManager.getEditions().get(selected).getYear() == confirmation){
                        this.editionManager.deleteEdition(selected);

                        menu.showMessage("\nThe edition was successfully deleted.");
                    }else {
                        menu.showMessage("\nFailed to delete Edition, try again");
                    }
                }else {
                    break;
                }
            }

        }else {
            this.menu.showMessage("There are no editions");
        }
    }



    private void conductorStart() {
        boolean running = true;
        while (running) {
            menu.showMessage("Entering execution mode..\n");
            playerManager = new PlayerManager();

            if (editionManager.areEditions(menuConductor.getYear())){
                Edition edition = editionManager.getYearEdition();
                menu.showMessage("--- The Trials "+ menuConductor.getYear() +" ---\n");
            }else{
                menuConductor.showNoEditionAvailable();}
        }
            running = false;

    }





}



