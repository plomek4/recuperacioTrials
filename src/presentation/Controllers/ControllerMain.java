package presentation.Controllers;

import Persistance.SelectedPersistance;
import business.Editions.Edition;
import business.Editions.EditionManager;
import business.Editions.PersistedEdition;
import business.Players.PlayerManager;
import business.Players.Player;
import business.Trials.Trial;
import business.Trials.TrialManager;
import business.Trials.Types.*;
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


    /**
     * Function that initiates the execution
     */
    public void run() throws IOException {
        String optionFaction;
        do {
            menu.showMainMenu();
            optionFaction = menu.askForString("Pick a faction: ");

            runFaction(optionFaction);
        } while (!Objects.equals(optionFaction, EXIT));
    }


    /**
     * Function that contains the logic to run each faction
     * @param optionFaction the selected faction option
     * @throws IOException
     */
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


    /**
     * Function that sends the selected persistance to all 3 managers
     * @throws IOException
     */
    private void persistanceToManagers() throws IOException {
        this.trialManager = new TrialManager(this.selectedFaction);
        this.editionManager = new EditionManager(this.selectedFaction);
        this.playerManager = new PlayerManager();
    }

    /**
     * Function that contains the logic to run each role
     * @param optionRole the selected role option
     * @throws IOException
     */
    private void runRole(String optionRole) throws IOException {
        switch (optionRole){
            case "A", "a" -> composerStart();
            case "B", "b" -> conductorStart();
            default -> menu.showMessage("\nWrong option. Enter 'A' or 'B'");
        }
    }


    /**
     * Function that starts the first option, the composer
     * @throws IOException
     */
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

    /**
     * Function that executes the option selected in the management mode
     * @param option the option selected
     * @throws IOException
     */
    private void runManage(int option) throws IOException{
        switch (option) {
            case 1 -> runTrials();
            case 2 -> runEditions();
            case 3 -> menu.showMessage("\nShutting down...");
            default -> menu.showMessage("\nWrong action number, enter a option between 1 and 3, both included");
        }
    }

    /**
     * Function that contains the logic to run the tials management
     * @throws IOException
     */
    private void runTrials() throws IOException {
        String option;
        do {
            menuComposer.showManageTrials();
            option = menu.askForString("Enter an option: ");

            runOptionTrials(option);
        } while (!Objects.equals(option, EXIT));
    }

    /**
     * Function that executes the option selected in the trials management mode
     * @param option the tials management mode option
     * @throws IOException
     */
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

    /**
     * Function that creates a new trial
     */
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


    /**
     * Function that shows the entire list of trials
     */
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


    /**
     * Function that deletes an especific trial
     */
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

    /**
     * Function that contains the logic to run the editions management
     * @throws IOException
     */
    private void runEditions() throws IOException {
        String option;
        do {
            menuComposer.showManageEditions();
            option = menu.askForString("Enter an option: ");

            runOptionEditions(option);
        } while (!Objects.equals(option, EXIT));

    }

    /**
     * Function that executes the option selected in the edition management mode
     * @param option the edition management mode option
     * @throws IOException
     */
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

    /**
     * Function that creates a new edition
     */
    private void doCreateEdition() {
        Edition edition = null;
        int editionYear;
        int initialNumberOfPlayers;
        int numberOfTrials;
        int trialsPicked = 0;
        int trialsPickedCounter = 0;
        List<String> pickedTrialsList = new LinkedList<>();

        while(true) {
            editionYear = menu.askForInteger("Enter the edition???s year: ");
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

    /**
     * Function that shows the entire edition list
     */
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

    /**
     * Function that duplicates an existent edition changing some aspects like year and number of players
     */
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
                        year = menu.askForInteger("Enter the edition???s year: ");
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


    /**
     * Funcions that deletes and especific edition chosen by the user
     */
    private void doDeleteEdition() {
        if (!this.editionManager.getEditions().isEmpty()) {
            int selected, confirmation;
            while (true){
                menu.showMessage("Which edition do you want to delete?\n");
                selected = menuComposer.pickAnEdition(editionManager.getEditions()) - 1;

                if (selected < (editionManager.getEditions().size())) {
                    editionManager.getEditions().get(selected).showTitle();
                    confirmation = menu.askForInteger("Enter the edition???s year for confirmation:");
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

    /**
     * Function that starts the second option, the composer
     * @return
     */
    private List<Player> conductorStart() {
        boolean running = true;
        List<Player> auxPlayerList = new LinkedList<>();
        while (running) {
            menuConductor.showMenuConductor();

            if (editionManager.existsPersistedEdition(editionManager.getEditionYear())) {
                PersistedEdition persistedEdition = editionManager.getCurrentSavedEdition();
                playerManager.setPlayers(persistedEdition.getPlayers());
                executeEdition(persistedEdition.getEdition(), persistedEdition.getTrials(),
                        persistedEdition.getNextTrialIndex() + 1);
            } else {
                if (editionManager.getEdition(menuConductor.getYear()) == null) {
                    menuConductor.showNoEditionAvailable();
                } else {
                    menuConductor.showNewEdition();

                    Edition edition = editionManager.getEdition(menuConductor.getYear());

                    playerManager.setPlayers(menuConductor.askForPlayers(edition.getPlayersQuantity()));
                    menu.showMessage("");
                    executeEdition(editionManager.getEdition(menuConductor.getYear()),
                            editionManager.getEdition(menuConductor.getYear()).getTrials(), 0);
                }
            }
            running = false;
        }
        editionManager.updatePersistedEditions();
        menu.showMessage("\nSaving & Shutting down...");
        return null;
    }

    /**
     * Function that executes an edition
     * @param edition the edition
     * @param trials the edition's trial
     * @param nextTrial the index for the trial
     */
    private void executeEdition(Edition edition, List<String> trials, int nextTrial) {
        for (int i = nextTrial; i < trials.size(); i++) {

            menu.showMessage("\nTrial #" + (i + 1) + " - " + trials.get(i));

            if (playerManager.arePlayersStillInGame()) {
                Trial trial = trialManager.getTrialByName(trials.get(i));

                if (trialManager.getTrialTypeByName(trials.get(i)) == Types.budget_request) {
                    executeBudgetRequestTrial((BudgetRequest) trial, playerManager.getPlayers());
                } else {
                    executeTrial(trial, playerManager.getPlayers());
                }

                if (playerManager.arePlayersStillInGame()) {
                    if (editionManager.isFinalTrial(edition, i)) {
                        if (!menuConductor.continueExecution()) {
                            editionManager.saveEdition(edition, trials, playerManager.getPlayers(), i);
                            break;
                        } else {
                            editionManager.saveEdition(edition, trials, playerManager.getPlayers(), i);
                        }
                    } else {
                        menu.showMessage("\nTHE TRIALS " + edition.getYear() + " HAVE ENDED - PLAYERS WON");
                    }
                }
            } else {
                menu.showMessage("\nTHE TRIALS " + edition.getYear() + " HAVE ENDED - PLAYERS LOST");
                break;
            }
        }
    }

    /**
     * Function that executes a trial
     * @param trial the trial
     * @param players the players list
     */
    private void executeTrial (Trial trial, List<Player> players) {
        List<Player> disqualifiedPlayers = new LinkedList<>();
        for (Player player : players) {
            trialManager.getTrialTypeByName(trial.getName());

            if (trialManager.getTrialTypeByName(trial.getName()) == Types.doctoral_thesis) {
                executeDoctoralThesisTrial((DoctoralThesis) trial, player);
            } else if (trialManager.getTrialTypeByName(trial.getName()) == Types.master_studies) {
                executeMasterStudiesTrial((MasterStudies) trial, player);
            } else {
                executePaperPublicationTrial((PaperPublication) trial, player);
            }
            if (player.isDisqualified()) {
                System.out.println("\t\tPlayer " + player.getName() + " is disqualified");
                disqualifiedPlayers.add(player);
            }
        }
        disqualifiedPlayers.forEach(players::remove);
        menu.showMessage("");
    }

    /**
     * Function that contains the logic to execute a trial which its type is : budget request
     * @param budgetRequest the trial's type
     * @param player a player list
     */
    private void executeBudgetRequestTrial(BudgetRequest budgetRequest, List<Player> player) {
        int totalIP = 0;
        boolean budgetGot = false;

        for (Player p : player){
            totalIP += p.getInvestigationPoints();
        }

        if (totalIP < budgetRequest.startTrial()){
            menu.showMessage("The research group failed the budget\n");
        }else {
            menu.showMessage("The research group got the budget\n");
            budgetGot = true;
        }

        for (Player p : player){
            if (budgetGot){
                float reward = budgetRequest.IPReward(p);
                p.addInvestigationPoints((int) reward);
                menu.showTabulatedMessage(p.getName() + " was successful. Congrats! PI count: " + p.getInvestigationPoints());
            }else {
                p.subtractInvestigationPoints(2);
                menu.showTabulatedMessage(p.getName() + " failed. PI count: " + p.getInvestigationPoints());
            }
        }
    }

    /**
     * Function that contains the logic to execute a trial which its type is : doctoral thesis
     * @param doctoralThesis the trial's type
     * @param player a single player
     */
    private void executeDoctoralThesisTrial(DoctoralThesis doctoralThesis, Player player) {
        if (player.getInvestigationPoints() > doctoralThesis.startTrial()) {
            if (Objects.equals(player.getRole(), "Master")) {
                player.setInvestigationPoints(5);
                menu.showMessageWithoutLN("\t" + player.getName() + " was successful. Congrats! IP count: "
                        + player.getInvestigationPoints());
                player.setRole("Doctor");
                menu.showTabulatedMessage("\t" + player.getName() + " is now a Doctor (with "
                        + player.getInvestigationPoints() + " IP). ");
            } else {
                player.addInvestigationPoints(5);
                menu.showMessageWithoutLN("\t" + player.getName() + " was successful. Congrats! IP count: "
                        + player.getInvestigationPoints());

                if (player.getInvestigationPoints() > player.getMaxPoints() && !Objects.equals(player.getRole(), "Doctor")) {
                    player.setRole(player.getNextRole());

                    menu.showTabulatedMessage("\t" + player.getName() + " is now a " + player.getNextRole() + " (with "
                            + player.getInvestigationPoints() + " IP). ");
                }
            }
        } else {
            player.subtractInvestigationPoints(5);
            menu.showTabulatedMessage(player.getName() + " Failed" + " IP count: " + player.getInvestigationPoints());
        }
    }

    /**
     * Function that contains the logic to execute a trial which its type is : master studies
     * @param masterStudies the trial's type
     * @param player a single player
     */
    private void executeMasterStudiesTrial(MasterStudies masterStudies, Player player) {
        int credits = 0;

        for (int i = 0; i < masterStudies.getCreditsQuantity(); i++) {
            if (masterStudies.runExecution() == 1) {
                credits++;
            }
        }
        // if player has won
        if (credits > (masterStudies.getCreditsQuantity() - credits)) {
            if (Objects.equals(player.getRole(), "Engineer")) {
                player.setInvestigationPoints(5);
                player.setRole("Master");
                menu.showMessage("\t" + player.getName() + " passed " + credits + "/"
                        + masterStudies.getCreditsQuantity() + " ECTS. Congrats! IP count: 10");
                menu.showMessage("\t\t" + player.getName() + " is now a Master (with "
                        + player.getInvestigationPoints() + " IP). ");
            } else {
                player.addInvestigationPoints(3);

                if (player.getInvestigationPoints() > player.getMaxPoints() && !Objects.equals(player.getRole(), "Doctor")) {
                    player.setRole(player.getNextRole());
                    player.setInvestigationPoints(5);

                    menu.showMessage("\t\t" + player.getName() + " is now a " + player.getNextRole() + " (with "
                            + player.getInvestigationPoints() + " IP). ");

                }
                menu.showMessage("\t" + player.getName() + " passed " + credits + "/"
                        + masterStudies.getCreditsQuantity() + " ECTS. Congrats! IP count: "
                        + player.getInvestigationPoints());
            }
        } else {
            player.subtractInvestigationPoints(3);
            menu.showMessage("\t" + player.getName() + " failed " + credits + "/"
                    + masterStudies.getCreditsQuantity() + " ECTS. Sorry! IP count: "
                    + player.getInvestigationPoints());
        }
    }

    /**
     * Function that contains the logic to execute a trial which its type is : paper publication
     * @param paperPublication the trial's type
     * @param player a single player
     */
    private void executePaperPublicationTrial(PaperPublication paperPublication, Player player) {
        int conclusion;

        menu.showMessageWithoutLN("\t" + player.getName() + " is submitting...");

        do {
            conclusion = paperPublication.startTrial();

            if (conclusion == 1) {
                if (Objects.equals(paperPublication.getQuartile(), "Q1")) {
                    player.addInvestigationPoints(4);
                } else if (Objects.equals(paperPublication.getQuartile(), "Q2")) {
                    player.addInvestigationPoints(3);
                } else if (Objects.equals(paperPublication.getQuartile(), "Q3")) {
                    player.addInvestigationPoints(2);
                } else {
                    player.addInvestigationPoints(1);
                }
                if (player.getInvestigationPoints() > player.getMaxPoints() && !Objects.equals(player.getRole(), "Doctor")) {
                    player.setRole(player.getNextRole());
                    player.setInvestigationPoints(5);
                    menu.showMessage("\t\t" + player.getName() + " is now a " + player.getNextRole() + " (with "
                            + player.getInvestigationPoints() + " IP). ");

                }
                menu.showMessageWithoutLN(" Accepted! PI count: " + player.getInvestigationPoints() + "\n");

            }else if (conclusion == 2){
                menu.showMessageWithoutLN(" Revisions... ");

            }else if (conclusion == 3){
                if (Objects.equals(paperPublication.getQuartile(), "Q1")) {
                    player.subtractInvestigationPoints(5);
                } else if (Objects.equals(paperPublication.getQuartile(), "Q2")) {
                    player.subtractInvestigationPoints(4);
                } else if (Objects.equals(paperPublication.getQuartile(), "Q3")) {
                    player.subtractInvestigationPoints(3);
                } else {
                    player.subtractInvestigationPoints(2);
                }
                menu.showMessageWithoutLN(" Rejected. PI count: " + player.getInvestigationPoints() + "\n");

            }
        } while (conclusion != 3 && conclusion != 1);
    }
}