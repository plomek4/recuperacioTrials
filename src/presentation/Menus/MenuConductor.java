package presentation.Menus;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        showMessage("--- The business.Trials 2021 ---");
        spacing();


    }


    public int getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    public int askForInteger(String message) {while (true) { try {System.out.print(message);return scanner.nextInt();} catch (InputMismatchException e) {System.out.println("That's not a valid integer, try again!");} finally {scanner.nextLine();}}}
    public String askForString(String message) {System.out.print(message); return scanner.nextLine();}
    public void showTabulatedMessage(String message) {System.out.println("\t" + message);}
    public void showMessage(String message) { System.out.println(message); }
    public void spacing() {System.out.println();}
}
