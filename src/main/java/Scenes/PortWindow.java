package Scenes;

import PortDescription.Dock;
import PortDescription.Port;
import ShipDescription.ShipDockConnector;
import javafx.stage.Stage;

/**
 * Created by Андрей on 29.03.2017.
 */
public class PortWindow implements Runnable {

    Dock dock1, dock2;
    Stage primaryStage;


    public PortWindow(Stage primaryStage){

        this.dock1=new Dock(new ShipDockConnector());
        this.dock2=new Dock(new ShipDockConnector());
        this.primaryStage=primaryStage;

    }



    public Dock GetDockByNumber(int number){
        if(number==1) return dock1;
        return dock2;
    }

    @Override
    public void run() {

    }
}
