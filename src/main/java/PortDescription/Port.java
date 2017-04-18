package PortDescription;

import CargoDescription.Cargo;
import Scenes.Map.MapPoint;
import ShipDescription.Ship;
import ShipDescription.ShipActions.ShipAction;
import StockDescription.Stock;
import StockDescription.StockAction.StockActionDB;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */

/**This class describes Port as an item of app*/
public class Port implements Runnable {

    private static final Logger log = Logger.getLogger(Port.class);
    /**All existing Ports*/
    private static Vector<Port> ports;
    /**Name of the Port*/
    private String portName;
    /**Coordinates of Port on Map*/
    private Point2D coord;
    /** Description of Port on Map*/
    private MapPoint mapPoint;
    /**Stock of the Port*/
    private Stock stock;
    /**Queues to I\O cargo to Stock*/
    private PriorityBlockingQueue<Cargo> putIntoStockQuque, getFromStockQuque;
    /**Queue of ships to Port*/
    private PriorityBlockingQueue<Ship> shipsQuque;
    /**Semaphore which allows ships to go inside the Port*/
    private Semaphore portSemaphore;
    //private Stage primaryStage;

    /**
     * Constructor of the Port
     * Inicializes port, starts Stock Threads to get and put Cargos
     * @param portName - Name of the Port
     * @param coord - Coordinates of Port on Map
     */
    public Port(String portName, Point2D coord) {

        //Инициализация порта на карте
        this.portName = portName;
        this.coord = coord;
        mapPoint = new MapPoint();
        mapPoint.getPointName().setText(String.valueOf(this.portName));

        mapPoint.getPoint().setOnMouseEntered(event ->
                mapPoint.getPointName().setVisible(true));

        mapPoint.getPoint().setOnMouseExited(event ->
                mapPoint.getPointName().setVisible(false));

        mapPoint.getPoint().setOnMousePressed(event -> {
            //Инициализация порта как логической единицы
            if (mapPoint.getPortTable().isVisible()) mapPoint.getPortTable().setVisible(false);
            else mapPoint.getPortTable().setVisible(true);
        });
        putIntoStockQuque = new PriorityBlockingQueue<>(2);
        getFromStockQuque = new PriorityBlockingQueue<>(2);
        portSemaphore = new Semaphore(2);
        stock = StockActionDB.GetStockState(portName);
        stock.setGetFromStockQuque(getFromStockQuque);
        stock.setPutIntoStockQuque(putIntoStockQuque);
        stock.getGetFromStockThread().start();
        stock.getPutIntoStockThread().start();
    }

    /**
     * Returns Coordinates of the Port
     * @return Coordinates
     */
    public Point2D getCoord() {
        return coord;
    }

    /**
     * @return MapPoint
     */
    public MapPoint getMapPoint() {
        return mapPoint;
    }

    /**
     * Returns name of the Port
     * @return PortName
     */
    public String getPortName() {
        return String.valueOf(portName);
    }

    /**@return shipsQueue*/
    public PriorityBlockingQueue<Ship> getShipsQuque() {
        return shipsQuque;
    }

    /**@return Semaphore of the Port*/
    public Semaphore getPortSemaphore() {
        return portSemaphore;
    }

    public Stock getStock() {
        return stock;
    }

    /**@return putIntoStockQuque*/
    public PriorityBlockingQueue<Cargo> getPutIntoStockQuque() {
        return putIntoStockQuque;
    }

    /**@return getFromStockQuque*/
    public PriorityBlockingQueue<Cargo> getGetFromStockQuque() {
        return getFromStockQuque;
    }

    /**@return all Ports*/
    public static Vector<Port> getPorts() {return ports;}

    /**
     * sets All Ports
     * @param ports - all ports*/
    public static void setPorts(Vector<Port> ports) {Port.ports = ports;}
    /**
     * Override method to start Thread of the Port
     * Gets ships from queue
     * starts ships threads if they are new
     */
    @Override
    public void run() {
        System.out.println(String.valueOf(this.portName));
        shipsQuque = ShipAction.GetShipsFromCurrentPort(String.valueOf(this.portName));
        while (true) {
            if (!shipsQuque.isEmpty()) {
                // if(portSemaphore.availablePermits()!=0) {
                Ship ship = shipsQuque.poll();
                if (ship.getState() == Thread.State.NEW) {
                    ship.setPort(this);
                    ship.start();
                }
            }
        }
    }

    /**
     * Input Port Description into file
     * @param portDescription - the description of Port
     */
    public void AddToPortFile(String portDescription){
        try (FileWriter out = new FileWriter(this.getPortName(), true)) {
            out.write(portDescription);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

}
