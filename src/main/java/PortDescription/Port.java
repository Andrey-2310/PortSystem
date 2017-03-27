package PortDescription;

import ShipActions.ShipAction;
import ShipDescription.Cargo;
import ShipDescription.Ship;
import javafx.geometry.Point2D;
import Scenes.Map.MapPoint;
import javafx.scene.image.Image;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Port implements Runnable {
    private String portName;
    private Point2D coord;
    private Vector<Dock> docks;
    private MapPoint mapPoint;
    private Stock stock;
    private BlockingQueue<Cargo> putIntoStockQuque, getFromStockQuque;
    private Queue<Ship> shipsQuque;
    private Semaphore portSemaphore;

    public Port(String portName, Point2D coord) {

        //Инициализация порта на карте
        this.portName = portName;
        this.coord = coord;
        mapPoint = new MapPoint(new Image("маячок.png"));
        mapPoint.getPointName().setText(this.portName);

        mapPoint.getPoint().setOnMouseEntered(event ->
                mapPoint.getPointName().setVisible(true));

        mapPoint.getPoint().setOnMouseExited(event ->
                mapPoint.getPointName().setVisible(false));

        mapPoint.getPoint().setOnMousePressed(event -> {
            //Инициализация порта как логической единицы
            putIntoStockQuque = new PriorityBlockingQueue<>();
            getFromStockQuque = new PriorityBlockingQueue<>();
            portSemaphore = new Semaphore(2);
            ShipAction shipAction = new ShipAction();
            shipsQuque = shipAction.GetShipsFromCurrentPort(this.portName);
            for (Ship ship : shipsQuque) {
                ship.setGetFromStock(getFromStockQuque);
                ship.setPutIntoStock(putIntoStockQuque);
                ship.setDockSemaphore(portSemaphore);
                ship.SetShipAction(shipAction);
                ship.start();
            }
        });
    }


    public Point2D getCoord() {
        return coord;
    }

    public void setCoord(Point2D coord) {
        this.coord = coord;
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    public String getPortName() {
        return portName;
    }

    @Override
    public void run() {

    }
}
