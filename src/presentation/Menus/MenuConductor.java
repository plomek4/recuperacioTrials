package presentation.Menus;

import business.Players.Player;

import java.util.*;

public class MenuConductor {
    private Scanner scanner;

    public MenuConductor() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Function that displays:
     */
    public void showMenuConductor(){
        spacing();
        showMessage("Entering execution mode...");
        spacing();
    }


    /**
     * Function that displays: Menú d'execució quan no hi ha edició per l'any actual
     */
    public void showNoEditionAvailable(){
        int year = getYear();
        showMessage("No edition is defined for the current year (" + year + ").");
        spacing();
        showMessage("Shutting down...");
    }


    /**
     * Function that displays: Menú quan s’inicia l’execució per l’any actual
     */
    public void showNewEdition(){
        showMessage("--- The Trials " + getYear() + " ---");
        spacing();
    }

    /**
     * Function that gets the current devive year
     * @return
     */
    public int getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Function that asks the user if he wants to continue the execution
     * @return the result
     */
    public boolean continueExecution(){
        while(true){
            String response = this.askForString("Continue the execution? [yes/no]: ").toLowerCase();

            if(response.equalsIgnoreCase("yes")){
                return true;
            } else if(response.equalsIgnoreCase("no")){
                return false;
            } else {
                showMessage("Wrong response. Please, try again with 'Yes' or 'No'.");
                this.spacing();
            }
        }
    }

    /**
     * Function that asks the user to name different players
     * @param iPlayers the number of players
     * @return the player list
     */
    public List<Player> askForPlayers(int iPlayers){
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < iPlayers; i++) {
            String name = this.askForStringNotEmpty("Enter the player's name (" + (i + 1) + "/" + iPlayers + "): ");

            players.add(new Player(name, 5, "Engineer"));
        }
        return players;
    }

    /**
     * Function that asks the user a String
     *
     * @param message the message
     */
    public String askForString(String message) {System.out.print(message); return scanner.nextLine();}

    /**
     * Function that asks the user a String that can't be empty
     *
     * @param message the message
     */
    public String askForStringNotEmpty(String message) {
        while (true) {
            String response = askForString(message);
            if (response.isEmpty()) {
                showMessage("The name cannot be empty. Please, try again.");
            } else {
                return response;
            }
        }
    }

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
