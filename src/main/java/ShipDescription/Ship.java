package ShipDescription;

import CargoDescription.Cargo;
import CargoDescription.CargoAction.CargoAction;
import PortDescription.Port;
import PortDescription.PortActions.PortAction;
import ShipDescription.ShipActions.ShipAction;
import org.apache.log4j.Logger;

import java.util.Vector;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Ship extends Thread implements Comparable<Ship> {

    /**
     * Numger of Ship
     */
    static int numgerOfShip = 0;
    /**
     * All Ship objects
     */
    private static Vector<Ship> ships = new Vector<>();
    /**
     * Logger
     */
    private static final Logger log = Logger.getLogger(Ship.class);
    /**
     * Name of ship
     */
    private String shipName;
    /**
     * Number of current Dock
     */
    private int numberOfDock;
    /**
     * current Port
     */
    private Port port;
    /**
     * Cargo on the board of Ship
     */
    private Cargo cargo;

    /**
     * Constructor of Ship object
     * Used to initialize Ship from DB
     *
     * @param shipName - Name of Ship
     */
    public Ship(String shipName) {
        this.shipName = shipName;
        System.out.println("Найден корабль " + shipName);
        this.cargo = CargoAction.GetCargoOfShip(shipName);
        ships.add(this);
    }

    /**
     * Constructor of Ship object
     * Used to initialize Ship with generating
     */

    public Ship() {

        this.shipName = "Generated" + numgerOfShip;
        System.out.println("Найден корабль " + shipName);

        this.cargo = CargoAction.GenerateCargo();
        ships.add(this);
        this.cargo.setShipID(ships.indexOf(this));

    }

    /**
     * Sets current Port
     */
    public void setPort(Port port) {
        this.port = port;
    }

    /**
     * Implementation of Comparable interface
     * used to build PriorityBlockingQueue
     *
     * @param ship - another Ship to compare with
     * @return
     */
    @Override
    public int compareTo(Ship ship) {
        if (this.getPriority() > ship.getPriority()) return -1;
        if (this.getPriority() < ship.getPriority()) return 1;
        return 0;
    }

    /**
     * Extending Thread  class
     * Description of Ship life cycle
     */
    @Override
    public void run() {
        try {
            log.info("Корабль " + shipName + " Ожидает разрешения");
            while (true) {
                port.getPortSemaphore().acquire();
                long time = System.currentTimeMillis();
                log.info("Разрешение для корабля " + shipName + " получено");


                numberOfDock = ShipAction.WhatDockIsEmpty(shipName) + 1;
                ShipAction.FulFillDoc(numberOfDock);
                log.info("Корабль" + shipName + " едет в " + numberOfDock + " док");

                port.getPutIntoStockQuque().offer(cargo);
                System.out.println(cargo.toString());
                if (!ships.contains(this)) {
                    log.info("Корабль" + shipName + " завершает свою работу");
                    this.interrupt();
                }
                do {
                    cargo = port.getGetFromStockQuque().poll();
                } while (cargo == null);
                System.out.println(cargo.toString());

                log.info("Корабль " + shipName + " покидает порт");
                ShipAction.ReleaseDock(numberOfDock);
                port.getPortSemaphore().release();
                time = System.currentTimeMillis() - time;
                System.out.println("Время нахождения корабля " + shipName + " в порту " + time);
                if (time > 5000) {
                    log.info("Время стоянки корабля " + shipName + " в порту " + port.getPortName() + " превышено на " + (time - 5000) + "мс");
                    ShipAction.AddShipIntoBlackList(time, this.shipName);
                }
                port = PortAction.ChooseNextPort(port.getPortName());
                log.info("Корабль " + shipName + " направляется в порт " + port.getPortName());
                port.getShipsQuque().add(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /** Gets all Ships */
    public static Vector<Ship> getShips() {
        return ships;
    }

}

