package PortDescription;

import ShipDescription.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Dock extends Thread {


    ShipDockConnector shipDockConnector;
    private int numberOfDock;
    Label dockLabel;
    TextField dockTextField;

    Button dockOkButton;

    public Dock(ShipDockConnector shipDockConnector, int numberOfDock) {
        this.numberOfDock=numberOfDock;
        this.shipDockConnector = shipDockConnector;
    }


    public void setShipDockConnector(ShipDockConnector shipDockConnector) {
        this.shipDockConnector = shipDockConnector;
    }

    public void setDockLabel(Label dockLabel) {
        this.dockLabel = dockLabel;
    }

    public void setDockOkButton(Button dockOkButton) {
        this.dockOkButton = dockOkButton;
    }


    public void setDockTextField(TextField dockTextField) {
        this.dockTextField = dockTextField;
    }

    @Override
    public void run() {
        this.shipDockConnector.SetTimeToStay(numberOfDock);

        this.shipDockConnector.SetCargoPriority(numberOfDock);
    }
}
