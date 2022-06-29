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

    //4.1 Selecció de format de persistència
    public void showMainMenu() {
        spacing();
        showMessage("The IEEE needs to know where your allegiance lies.");
        spacing();
        showTabulatedMessage("I) People's front of Engineering (CSV)");
        showTabulatedMessage("II) Engineering people's Front (JSON)");
        spacing();
    }


    //4.2 Selecció de rol
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


    public void showLogo(){
        System.out.println(" ____ _           _____      _       _\n" +
                "/__  \\ |__   ___ /__   \\_ __(_) __ _| |___\n" +
                " / /\\/ '_ \\ / _ \\  / /\\/ '__| |/ _` | / __|\n" +
                "/ /  | | | |  __/ / /  | |  | | (_| | \\__ \\\n" +
                "\\/   |_| |_|\\___| \\/   |_|  |_|\\__,_|_|___/\n");
    }

    public int askForInteger(String message) {while (true) { try {System.out.print(message);return scanner.nextInt();} catch (InputMismatchException e) {System.out.println("That's not a valid integer, try again!");} finally {scanner.nextLine();}}}
    public String askForString(String message) {System.out.print(message); return scanner.nextLine();}

    public String askForNameNotEmpty(String message) {
        while (true) {
            String response = askForString(message);
            if (response.isEmpty()) {
                showMessage("The name cannot be empty. Please, try again.");
            } else {
                return response;
            }
        }
    }
    public void showTabulatedMessage(String message) {
        System.out.println("\t" + message);
    }
    public void showMessage(String message) { System.out.println(message); }

    public void showMessageWithoutLN(String message) { System.out.print(message); }
    public void spacing() {
        System.out.println();
    }

}
