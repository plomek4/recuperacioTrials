package presentation.Menus;

import business.Trials.Trial;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuMain {
    private Scanner scanner;

    public MenuMain() {
        this.scanner = new Scanner(System.in);
    }


    /**
     * Function that displays: 4.1 Selecció de format de persistència
     */
    public void showMainMenu() {
        spacing();
        showMessage("The IEEE needs to know where your allegiance lies.");
        spacing();
        showTabulatedMessage("I) People's front of Engineering (CSV)");
        showTabulatedMessage("II) Engineering people's Front (JSON)");
        spacing();
    }

    /**
     * Function that displays: 4.2 Selecció de rol
     */
    public void showMenuRole() {
        spacing();
        showLogo();
        spacing();
        showMessage("Welcome to the trials. Who are you?");
        spacing();
        showTabulatedMessage("A) The Composer");
        showTabulatedMessage("B) This year's Conductor");
        spacing();
    }

    /**
     * Function that displays the logo of the program
     */
    public void showLogo(){
        System.out.println(" ____ _           _____      _       _\n" +
                "/__  \\ |__   ___ /__   \\_ __(_) __ _| |___\n" +
                " / /\\/ '_ \\ / _ \\  / /\\/ '__| |/ _` | / __|\n" +
                "/ /  | | | |  __/ / /  | |  | | (_| | \\__ \\\n" +
                "\\/   |_| |_|\\___| \\/   |_|  |_|\\__,_|_|___/\n");
    }

    /**
     * Function that asks the user an integer
     * @param message the message
     * @return
     */
    public int askForInteger(String message) {while (true) { try {System.out.print(message);return scanner.nextInt();} catch (InputMismatchException e) {System.out.println("That's not a valid integer, try again!");} finally {scanner.nextLine();}}}

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
     * Function that displays a message without a new line
     * @param message the message
     */
    public void showMessageWithoutLN(String message) { System.out.print(message); }

    /**
     * Function that displays a new line
     */
    public void spacing() {System.out.println();}

}
