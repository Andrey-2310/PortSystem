package sample;

import Scenes.Map.MapWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Set;

/**
 * Main class which starts the app (main graphical thread)
 */
public class Main extends Application {
    /**
     * Starting the app
     * @param primaryStage - app Stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Port Dispatch System");

        MapWindow mapWindow=new MapWindow();
        mapWindow.CreatingMapWindow(primaryStage);
       // shipScene= shipsWindow.CreatingShipsWindow(primaryStage, mapScene);

    }

    /**
     * Stopping the main thread
     */
 @Override
 public void stop(){
     Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

     Platform.exit();
     System.exit(0);
 }

    /**
     * launching args from command line
     * @param args - arguments from command line
     */
    public static void main(String[] args) {
        launch(args);
    }
}
