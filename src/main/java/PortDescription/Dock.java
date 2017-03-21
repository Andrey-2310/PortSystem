package PortDescription;

import ShipDescription.*;
import ShipActions.ShipAction;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Dock extends Thread {

    ShipDockConnector shipDockConnector;

    public Dock(ShipDockConnector shipDockConnector) {
        this.shipDockConnector = shipDockConnector;
    }


    @Override
    public void run() {
        this.shipDockConnector.SetTimeToStay();
        this.shipDockConnector.SetCargoPriority();
    }
}
