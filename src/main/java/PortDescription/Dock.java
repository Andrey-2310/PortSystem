package PortDescription;

import ShipDescription.*;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Dock extends Thread {


    ShipDockConnector shipDockConnector;

    public Dock(ShipDockConnector shipDockConnector) {
        this.shipDockConnector = shipDockConnector;
    }


    public void setShipDockConnector(ShipDockConnector shipDockConnector) {
        this.shipDockConnector = shipDockConnector;
    }

    @Override
    public void run() {
        this.shipDockConnector.SetTimeToStay();
        this.shipDockConnector.SetCargoPriority();
    }
}
