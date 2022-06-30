package presentation.Menus;

import business.Editions.Edition;
import business.Trials.Trial;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuComposer {
    private Scanner scanner;

    public MenuComposer() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Function that displays: 4.3 Menú de gestió (Compositor)
     */
    public void showMenuComposer() {
        spacing();
        showTabulatedMessage("1) Manage Trials");
        showTabulatedMessage("2) Manage Editions");
        spacing();
        showTabulatedMessage("3) Exit");
        spacing();
    }


    /**
     * Function that displays: 4.3.1 Submenú de gestió de proves
     */
    public void showManageTrials() {
        spacing();
        showTabulatedMessage("a) Create Trial");
        showTabulatedMessage("b) List Trials");
        showTabulatedMessage("c) Delete Trial");
        spacing();
        showTabulatedMessage("d) Back");
        spacing();
    }



    /**
     * Function that displays: 4.3.1.1 Creació de proves
     */
    public void showCreateTrial(){
        spacing();
        showTabulatedMessage("--- Trial types ---");
        spacing();
        showTabulatedMessage("1) Paper publication");
        showTabulatedMessage("2) Master studies");
        showTabulatedMessage("3) Doctoral thesis defense");
        showTabulatedMessage("4) Budget request");
        spacing();
    }




    /**
     * Function that displays: 4.3.2 Submenú de gestió d’edicions
     */
    public void showManageEditions(){
        spacing();
        showTabulatedMessage("a) Create Edition");
        showTabulatedMessage("b) List Editions");
        showTabulatedMessage("c) Duplicate Edition");
        showTabulatedMessage("d) Delete Edition");
        spacing();
        showTabulatedMessage("e) Back");
    }


    /**
     * Function that asks a probability between 0 and 100
     * @return the probability
     */
    public int askPassProbability() {
        int number;
        while(true) {
            number = askForInteger("Enter the credit pass probability: ");

            if (number < 0 || number > 100){
                showMessage("Value must be between 0 and 100, try again");
            }else{
                break;
            }
        }
        return number;

    }

    /**
     * Function that asks a number of Ects between 60 and 120
     * @return the number of ects
     */
    public int askEctsNumber() {
        int number;
        while(true) {
            number = askForInteger("Enter the master’s ECTS number: ");

            if (number < 60 || number > 120){
                showMessage("Value must be between 60 and 120, try again");
            }else{
                break;
            }
        }
        return number;
    }


    /**
     * Function that asks a trial type between 1 and 4
     * @return the option
     */
    public int askTrialType(){
        int option;
        do {
            this.showCreateTrial();
            option = this.askForInteger("Enter the trial’s type: ");

        } while (option != 1 && option != 2 && option != 3 && option != 4);

        return option;
    }

    /**
     * Function that asks a budget between 1000 and 2000000000
     * @return the budget number
     */
    public int askForBudget() {
        int number;
        while(true) {
            number = askForInteger("Enter the budget amount: ");

            if (number < 1000 || number > 2000000000){
                showMessage("Budget amount must be between 1000 and 2000000000, try again");
            }else{
                break;
            }
        }
        return number;
    }

    /**
     * Function that asks a probability between 0 and 100
     * @return the probability
     */
    public int askForDifficulty() {
        int number;
        while(true) {
            number = askForInteger("Enter the credit pass probability: ");

            if (number < 1 || number > 100){
                showMessage("Value must be between 0 and 100, try again");
            }else{
                break;
            }
        }
        return number;
    }

    /**
     * Function that asks a percentage between 0 and 100
     * @return the probability
     */
    public int askPercentage(String msg) {
        int number;
        while(true) {
            number = askForInteger(msg);

            if (number < 1 || number > 100){
                showMessage("Value must be between 0 and 100, try again");
            }else{
                break;
            }
        }
        return number;
    }


    /**
     * Function that asks for a quartile
     * @return the quartile
     */
    public String askForQuartile() {
        String quartile;
        while (true){
            quartile = this.askForString("Enter the journal’s quartile: ");

            if (!quartile.equals("Q1") && !quartile.equals("Q2") && !quartile.equals("Q3") && !quartile.equals("Q4")){
                showMessage("That is not a valid quartile");
            }else{
                break;
            }
        }
        return quartile;
    }

    /**
     *Function that displays all trials in a list
     * @param trials the trial list
     */
    private void showTrials(List<Trial> trials) {
        for (int i = 0; i < trials.size(); i++){
            showMessage("\t"+ (i + 1) + ") " + trials.get(i).getName());
        }
        showMessage("\n\t" + (trials.size() + 1) + ") Back");
    }

    /**
     * Function that asks the user to pick a trial
     * @param trials the trial list
     * @return the option
     */
    public int pickATrial(List<Trial> trials) {
        showTrials(trials);
        return askForInteger("\nEnter an option: ");
    }

    /**
     * Function that asks the user to pick an edition
     * @param editions the edition list
     * @return the option
     */
    public int pickAnEdition(List<Edition> editions) {
        showEditions(editions);
        return askForInteger("\nEnter an option: ");
    }

    /**
     *Function that displays all editions in a list
     * @param editions the edition list
     */
    public void showEditions(List<Edition> editions) {
        for (int i = 0; i < editions.size(); i++){
            showMessage("\t"+ (i + 1) + ") The Trials " + editions.get(i).getYear());
        }
        showMessage("\n\t" + (editions.size() + 1) + ") Back");
    }

    /**
     * Function that asks the user an integer
     * @param message the message
     * @return
     */
    public int askForInteger(String message) {while (true) { try {System.out.print(message);return scanner.nextInt();} catch (InputMismatchException e) {System.out.println("\nThat's not a valid integer, try again!");} finally {scanner.nextLine();}}}

    /**
     * Function that asks the user a String
     *
     * @param message the message
     */
    public String askForString(String message) {System.out.print(message); return scanner.nextLine();}

    /**
     * Function that displays a message with a tabulation
     * @param message the message
     */
    public void showTabulatedMessage(String message) {System.out.println("\t" + message);}

    /**
     * Function that displays a message
     * @param message the message
     */
    public void showMessage(String message) { System.out.println(message); }

    /**
     * Function that displays a new line
     */
    public void spacing() {System.out.println();}

}
