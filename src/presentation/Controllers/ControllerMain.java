package presentation.Controllers;

import presentation.Menus.MenuMain;

import java.io.IOException;
import java.util.Objects;

public class ControllerMain {
    private static final String EXIT = "impossibleWord";

    private MenuMain menu;
    private ControllerComposer controllerComposer;
    private ControllerConductor controllerConductor;

    public ControllerMain(MenuMain menu, ControllerComposer controllerComposer, ControllerConductor controllerConductor) {
        this.menu = menu;
        this.controllerComposer = controllerComposer;
        this.controllerConductor = controllerConductor;
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
        switch (optionFaction) {
            case "I", "i" -> csvFaction();
            case "II", "ii" -> jsonFaction();
            default -> menu.showMessage("\nWrong option. Enter 'I' or 'II'");
        }
    }


    private void csvFaction() throws IOException {
        String optionRole;
        do {
            menu.showMenuRole();
            optionRole = menu.askForString("Enter a role: ");

            runRole(optionRole);
        } while (!Objects.equals(optionRole, EXIT));

    }

    private void jsonFaction() throws IOException {
        String optionRole;


        do {
            menu.showMenuRole();
            optionRole = menu.askForString("Enter a role: ");

            runRole(optionRole);
        } while (!Objects.equals(optionRole, EXIT));
    }



    private void runRole(String optionRole) throws IOException {
        switch (optionRole){
            case "A", "a" -> controllerComposer.run();
            case "B", "b" -> controllerConductor.run();
            default -> menu.showMessage("\nWrong option. Enter 'A' or 'B'");
        }
    }

}



