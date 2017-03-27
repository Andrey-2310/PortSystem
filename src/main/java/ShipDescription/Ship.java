package ShipDescription;

import PortActions.PortAction;
import PortDescription.Dock;
import PortDescription.Port;
import ShipActions.ShipAction;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Ship extends Thread implements Comparable<Ship> {


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


    public Ship(String shipName) {
        this.shipName = shipName;
        System.out.println("Найден корабль " + shipName);
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
            System.out.println("Корабль " + shipName + " Ожидает разрешения");
            portSemaphore.acquire();
            System.out.println("Разрешение для корабля " + shipName + " получено");

            numberOfDock = shipAction.WhatDockIsEmpty(shipName) + 1;
            shipAction.FulFillDoc(numberOfDock);
            System.out.println("Корабль едет в " + numberOfDock + " порт");

            shipDockConnector = new ShipDockConnector();
            currentDock = new Dock(shipDockConnector);
            currentDock.start();
            System.out.println("Время стоянки в порту: " + shipDockConnector.GetTimeToStay());
            System.out.println("Приоритет груза: " + shipDockConnector.GetCargoPriority());
            currentDock.join();

            System.out.println("Корабль " + shipName + " покидает порт");
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