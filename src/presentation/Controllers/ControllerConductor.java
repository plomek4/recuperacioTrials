package presentation.Controllers;

import presentation.Menus.MenuConductor;

public class ControllerConductor {
    private MenuConductor menuConductor;

    public ControllerConductor(MenuConductor menu) {
        this.menuConductor = menu;
    }


    public void run() {
        menuConductor.showMessage("Entering management mode...");
        int option;
        do {
            menuConductor.showMenuConductor();
            option = menuConductor.askForInteger("Enter an option: ");
            runOption(option);
        } while (option != 3);
    }

    private void runOption(int option) {
        switch (option) {
            case 1 -> menuConductor.showMessage("xd");//manageTrials();
            case 2 -> menuConductor.showMessage("xd");//manageEditions();
            case 3 -> menuConductor.showMessage("\nShutting down...");
            default -> menuConductor.showMessage("\nWrong action number, enter a option between 1 and 3, both included");
        }
    }

}
