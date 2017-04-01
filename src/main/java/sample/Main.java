package sample;

import PortActions.PortAction;
import PortDescription.Port;
import Scenes.CreateCargoListWindow;
import Scenes.Map.MapWindow;
import Scenes.PortWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Vector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Port Dispatch System");
        //PortAction portAction = new PortAction();
       // Vector<Port> ports = portAction.GenerateAllPorts(primaryStage);
       /* portAction.StartAllPortThreads(portAction.ConstuctAllPortThreads(ports));*/
        //new CreateCargoListWindow(primaryStage);
        new MapWindow(primaryStage);
       // PortWindow portWindow=new PortWindow(primaryStage);
        //portWindow.CreatePortWindow();
       // primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
