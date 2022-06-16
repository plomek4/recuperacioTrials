package presentation.Menus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuComposer {
    private Scanner scanner;

    public MenuComposer() {
        this.scanner = new Scanner(System.in);
    }


    //4.3 Menú de gestió (Compositor)
    public void showMenuComposer() {
        spacing();
        showTabulatedMessage("1) Manage Trials");
        showTabulatedMessage("2) Manage Editions");
        spacing();
        showTabulatedMessage("3) Exit");
        spacing();
    }


    //4.3.1 Submenú de gestió de proves
    public void showManageTrials() {
        spacing();
        showTabulatedMessage("a) Create Trial");
        showTabulatedMessage("b) List Trials");
        showTabulatedMessage("c) Delete Trial");
        spacing();
        showTabulatedMessage("d) Back");
        spacing();
    }


    //4.3.1.1 Creació de proves
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


    //4.3.1.2 Llistat de proves
    public void showListTrials(){
        spacing();
        showMessage("Here are the current trials, do you want to see more details or go back?");
        spacing();

        showTabulatedMessage("x) Back");
    }

    //4.3.1.3 Eliminació de proves
    public void showDeleteTrial(){
        spacing();
        showMessage("Which trial do you want to delete?");
        spacing();
        showTabulatedMessage("x) Back");
    }

    //4.3.2 Submenú de gestió d’edicions
    public void showManageEditions(){
        spacing();
        showTabulatedMessage("a) Create Edition");
        showTabulatedMessage("b) List Editions");
        showTabulatedMessage("c) Duplicate Edition");
        showTabulatedMessage("d) Delete Edition");
        spacing();
        showTabulatedMessage("e) Back");
    }

    //4.3.2.1 Creació d’edicions
    public void showCreateEdition(){
        spacing();
        showMessage("Enter the edition’s year: ");
        showMessage("Enter the initial number of players: ");
        showMessage("Enter the number of trials: ");
        spacing();
        showTabulatedMessage("--- Trials ---");

    }

    //4.3.2.2 Llistat de d’edicions
    public void showListEditions(){
        spacing();
        showMessage("Here are the current editions, do you want to see more details or go back?");
        spacing();


        showTabulatedMessage("x) Back");
    }

    //4.3.2.3 Duplicació d’edicions
    public void showDuplicateEditions(){
        spacing();
        showMessage("Which edition do you want to clone?");
        spacing();


        showTabulatedMessage("x) Back");
    }

    //4.3.2.4 Eliminació d’edicions
    public void showDeleteEdition(){
        spacing();
        showMessage("Which edition do you want to delete?");
        spacing();

        showTabulatedMessage("x) Back");
    }




    public int askForInteger(String message) {while (true) { try {System.out.print(message);return scanner.nextInt();} catch (InputMismatchException e) {System.out.println("\nThat's not a valid integer, try again!");} finally {scanner.nextLine();}}}
    public String askForString(String message) {System.out.print(message); return scanner.nextLine();}
    public void showTabulatedMessage(String message) {System.out.println("\t" + message);}
    public void showMessage(String message) { System.out.println(message); }
    public void spacing() {System.out.println();}
}
