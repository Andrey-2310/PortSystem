package ShipDescription;

import CargoDescription.Cargo;
import PortDescription.Dock;
import Scenes.PortWindow;
import ShipDescription.ShipActions.ShipAction;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Ship implements Comparable<Ship>, Runnable {

    private static final Logger log = Logger.getLogger(Ship.class);
    private String shipName;
    private int shipPrioity;
    private int numberOfDock;
    private BlockingQueue<Cargo> putIntoStock, getFromStock;
    private Dock currentDock;
    private Semaphore portSemaphore;
    private Cargo cargo;
    private ShipDockConnector shipDockConnector;
    private GetFromShipStream getFromShipStream;
    private PutIntoShipStream putIntoShipStream;
    private ShipAction shipAction;
    private PortWindow portWindow;


    public Ship(String shipName) {
        this.shipName = shipName;
        System.out.println("Найден корабль " + shipName);
    }

    public void setPortWindow(PortWindow portWindow) {
        this.portWindow = portWindow;
    }

    public void SetShipAction(ShipAction shipAction) {
        this.shipAction = shipAction;
    }

    public void setDockSemaphore(Semaphore portSemaphore) {
        this.portSemaphore = portSemaphore;
    }

    public GetFromShipStream getGetFromShipStream() {
        return getFromShipStream;
    }

    public PutIntoShipStream getPutIntoShipStream() {
        return putIntoShipStream;
    }

    public void setShipPrioity(int shipPrioity) {
        this.shipPrioity = shipPrioity;
    }

    public void setPutIntoStock(BlockingQueue<Cargo> putIntoStock) {
        this.putIntoStock = putIntoStock;
    }

    public void setGetFromStock(BlockingQueue<Cargo> getFromStock) {
        this.getFromStock = getFromStock;
    }

    @Override
    public int compareTo(Ship ship) {
        if (this.shipPrioity > ship.shipPrioity) return -1;
        if (this.shipPrioity < ship.shipPrioity) return 1;
        return 0;
    }

    @Override
    public void run() {
        try {
            Thread shipThread=new Thread(this);
            log.info("Корабль " + shipName + " Ожидает разрешения");
            portSemaphore.acquire();
            log.info("Разрешение для корабля " + shipName + " получено");


            numberOfDock = shipAction.WhatDockIsEmpty(shipName) + 1;
            shipAction.FulFillDoc(numberOfDock);
            log.info("Корабль" +shipName +" едет в " + numberOfDock + " док");
            Thread.currentThread().sleep(3000);
         /*   shipDockConnector = new ShipDockConnector(portWindow);
            currentDock =  portWindow.GetDockByNumber(numberOfDock);
            currentDock.setShipDockConnector(shipDockConnector);
            currentDock.start();
            log.info("Время стоянки в порту: " + shipDockConnector.GetTimeToStay());
            log.info("Приоритет груза: " + shipDockConnector.GetCargoPriority());
            currentDock.join();*/

            log.info("Корабль " + shipName + " покидает порт");
            shipAction.ReleaseDock(numberOfDock);
            portSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PutIntoShipStream implements Runnable {
    PutIntoShipStream() {
    }

    @Override
    public void run() {

    }
}

class GetFromShipStream implements Runnable {
    @Override
    public void run() {

    }
}