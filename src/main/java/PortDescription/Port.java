package PortDescription;

import Scenes.CreateCargoListWindow;
import ShipDescription.ShipActions.ShipAction;
import CargoDescription.Cargo;
import ShipDescription.Ship;
import javafx.geometry.Point2D;
import Scenes.Map.MapPoint;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Port implements Runnable {
    private static StringBuffer portName;
    private Point2D coord;
    private Vector<Dock> docks;
    private MapPoint mapPoint;
    private Stock stock;
    private BlockingQueue<Cargo> putIntoStockQuque, getFromStockQuque;
    private Queue<Ship> shipsQuque;
    private Semaphore portSemaphore;

    public Port(String portName, Point2D coord, Stage primaryStage) {

        //Инициализация порта на карте
        this.portName = new StringBuffer(portName);
        this.coord = coord;
        mapPoint = new MapPoint(new Image("маячок.png"));
        mapPoint.getPointName().setText(String.valueOf(this.portName));

        mapPoint.getPoint().setOnMouseEntered(event ->
                mapPoint.getPointName().setVisible(true));

        mapPoint.getPoint().setOnMouseExited(event ->
                mapPoint.getPointName().setVisible(false));

        mapPoint.getPoint().setOnMousePressed(event -> {
            //Инициализация порта как логической единицы
            ShipAction shipAction = new ShipAction();
            shipsQuque = shipAction.GetShipsFromCurrentPort(String.valueOf(this.portName));
            new CreateCargoListWindow(primaryStage, shipsQuque.size());
         /*   putIntoStockQuque = new PriorityBlockingQueue<>();
            getFromStockQuque = new PriorityBlockingQueue<>();
            portSemaphore = new Semaphore(2);
            for (Ship ship : shipsQuque) {
                ship.setGetFromStock(getFromStockQuque);
                ship.setPutIntoStock(putIntoStockQuque);
                ship.setDockSemaphore(portSemaphore);
                ship.SetShipAction(shipAction);
                ship.start();
            }*/
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

    public static String getPortName() {return String.valueOf(portName);}

    @Override
    public void run() {

    }
}
