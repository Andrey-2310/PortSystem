package sample;

import PortActions.PortAction;
import PortDescription.Dock;
import PortDescription.Port;
import Scenes.MapWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Vector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        PortAction portAction = new PortAction();
        Vector<Port> ports = portAction.GenerateAllPorts();
       /* portAction.StartAllPortThreads(portAction.ConstuctAllPortThreads(ports));*/

        new MapWindow(primaryStage, ports);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
