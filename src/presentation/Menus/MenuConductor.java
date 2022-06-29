package presentation.Menus;

import business.Players.Types.Player;

import java.util.*;

public class MenuConductor {
    private Scanner scanner;

    public MenuConductor() {
        this.scanner = new Scanner(System.in);
    }


    //4.4 Menú d’execució (Conductor)
    public void showMenuConductor(){
        spacing();
        showMessage("Entering execution mode...");
        spacing();
    }

    //Menú d'execució quan no hi ha edició per l'any actual
    public void showNoEditionAvailable(){
        int year = getYear();
        showMessage("No edition is defined for the current year (" + year + ").");
        spacing();
        showMessage("Shutting down...");
    }


    //Menú quan s’inicia l’execució per l’any actual
    public void showNewEdition(){
        showMessage("--- The Trials " + getYear() + " ---");
        spacing();
    }

    public int getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public boolean continueExecution(){
        while(true){
            String response = this.askForString("Continue the execution? [yes/no]: ").toLowerCase();

            this.spacing();

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

    public List<Player> askForPlayers(int iPlayers){
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < iPlayers; i++) {
            String name = this.askForStringNotEmpty("Enter the player's name (" + (i + 1) + "/" + iPlayers + "): ");

            players.add(new Player(name, 5, "Engineer"));
        }
        return players;
    }

    public int askForInteger(String message) {while (true) { try {System.out.print(message);return scanner.nextInt();} catch (InputMismatchException e) {System.out.println("That's not a valid integer, try again!");} finally {scanner.nextLine();}}}
    public String askForString(String message) {System.out.print(message); return scanner.nextLine();}
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
    public void showTabulatedMessage(String message) {System.out.println("\t" + message);}
    public void showMessage(String message) { System.out.println(message); }
    public void spacing() {System.out.println();}
}
