import presentation.Controllers.ControllerComposer;
import presentation.Controllers.ControllerConductor;
import presentation.Controllers.ControllerMain;
import presentation.Menus.MenuComposer;
import presentation.Menus.MenuConductor;
import presentation.Menus.MenuMain;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MenuMain menu = new MenuMain();
        MenuComposer menuComposer = new MenuComposer();
        MenuConductor menuConductor = new MenuConductor();
        ControllerConductor controllerConductor = new ControllerConductor(menuConductor);
        ControllerComposer controllerComposer = new ControllerComposer(menuComposer);

        ControllerMain controllerMain = new ControllerMain(menu, controllerComposer, controllerConductor);
        controllerMain.run();

    }
}
