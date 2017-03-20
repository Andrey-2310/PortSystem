package PortDescription;

import ShipDescription.*;
import ShipActions.ShipAction;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Dock {
    private BlockingQueue<Cargo> putIntoStock, getFromStock;
    Semaphore dockSemaphore;
    Vector<Ship> docShips;
    private int numberOfDock;
    Dock(int numberOfDock, BlockingQueue<Cargo> putIntoStock, BlockingQueue<Cargo> getFromStock){
        this.putIntoStock=putIntoStock;
        this.getFromStock=getFromStock;
        dockSemaphore= new Semaphore(1);
        this.numberOfDock=numberOfDock;
        ShipAction shipAction=new ShipAction();
        docShips=shipAction.GetShipsFromCurrentDock(this.numberOfDock);
    }
    public void WorkWithDock(){
       /* ships.add(new Ship("Gabella", dockSemaphore));
        ships.add(new Ship("Biba", dockSemaphore));
        ships.add(new Ship("Dick", dockSemaphore));*/
        for(Ship ship:docShips)
            new Thread((ship)).start();
    }

}
