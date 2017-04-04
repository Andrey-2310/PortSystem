package ShipDescription;

import CargoDescription.Cargo;
import CargoDescription.CargoAction.CargoAction;
import PortDescription.Dock;
import PortDescription.Port;
import Scenes.Map.MapWindow;
import ShipDescription.ShipActions.ShipAction;
import org.apache.log4j.Logger;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Ship extends Thread implements Comparable<Ship> {

    private static final Logger log = Logger.getLogger(Ship.class);
    private String shipName;
  //  private int shipPriority;
    private int numberOfDock;
    private PriorityBlockingQueue<Cargo> putIntoStockQuque, getFromStockQuque;
    private Dock currentDock;
    private Port port;
    private Semaphore portSemaphore;
    private Cargo cargo;
    private ShipDockConnector shipDockConnector;


    public Ship(String shipName) {
        this.shipName = shipName;
        System.out.println("Найден корабль " + shipName);
        CargoAction action = new CargoAction();
        this.cargo = action.GetCargoOfShip(shipName);
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public void setDockSemaphore(Semaphore portSemaphore) {
        this.portSemaphore = portSemaphore;
    }

  /*  public void setShipPrioity(int shipPrioity) {
        this.shipPriority = shipPrioity;
    }*/

    public void setPutIntoStock(PriorityBlockingQueue<Cargo> putIntoStockQuque) {
        this.putIntoStockQuque = putIntoStockQuque;
    }

    public void setGetFromStock(PriorityBlockingQueue<Cargo> getFromStockQuque) {
        this.getFromStockQuque = getFromStockQuque;
    }

    @Override
    public int compareTo(Ship ship) {
        if (this.getPriority() > ship.getPriority()) return -1;
        if (this.getPriority() < ship.getPriority()) return 1;
        return 0;
    }

    @Override
    public void run() {
        try {
            log.info("Корабль " + shipName + " Ожидает разрешения");
            while (true) {
                portSemaphore = port.getPortSemaphore();
                portSemaphore.acquire();
                log.info("Разрешение для корабля " + shipName + " получено");

                ShipAction shipAction = new ShipAction();
                numberOfDock = shipAction.WhatDockIsEmpty(shipName) + 1;
                shipAction.FulFillDoc(numberOfDock);
                log.info("Корабль" + shipName + " едет в " + numberOfDock + " док");

                putIntoStockQuque = port.getPutIntoStockQuque();
                getFromStockQuque = port.getGetFromStockQuque();

                putIntoStockQuque.offer(cargo);
                System.out.println(cargo.toString());
               // Thread.currentThread().sleep(2000);
                do {
                    cargo = getFromStockQuque.poll();
                }while(cargo==null);
                System.out.println(cargo.toString());

                log.info("Корабль " + shipName + " покидает порт");
                shipAction.ReleaseDock(numberOfDock);
                portSemaphore.release();

                port = MapWindow.ChooseNextPort(port.getPortName());
                log.info("Корабль " + shipName + " направляется в порт " + port.getPortName());
                port.getShipsQuque().add(this);
               /* while (true) {
                    if (!port.getShipsQuque().contains(this)) break;
                }*/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

