package PortDescription;

import Scenes.PortWindow;
import ShipDescription.ShipActions.ShipAction;
import CargoDescription.Cargo;
import ShipDescription.Ship;
import StockDescription.Stock;
import StockDescription.StockAction.StockActionDB;
import StockDescription.StockAction.StockActionInterface;
import com.mysql.cj.mysqlx.protobuf.MysqlxCrud;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import Scenes.Map.MapPoint;
import javafx.stage.Stage;

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
    //private Stage primaryStage;
    private volatile PortWindow portWindow;

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
        putIntoStockQuque = new PriorityBlockingQueue<>();
        getFromStockQuque = new PriorityBlockingQueue<>();
        portSemaphore = new Semaphore(2);
        StockActionInterface stockAction = new StockActionDB();
        stock = stockAction.GetStockState(portName);
        Thread monitoring = new Thread(() -> {
            while (true)
                try {
                    Thread.currentThread().sleep(1000);
                    System.out.println(portSemaphore.getQueueLength());
                    Platform.runLater(() -> {
                        ObservableList<MapPoint.DataModel> portDescription = FXCollections.observableArrayList(
                                new MapPoint.DataModel("Quque length", portSemaphore.getQueueLength()),
                                new MapPoint.DataModel("Ordinary", stock.getOrdinary()),
                                new MapPoint.DataModel("Explosive", stock.getExplosive()),
                                new MapPoint.DataModel("Poisonous", stock.getPoisonous()),
                                new MapPoint.DataModel("Sensetive", stock.getSensetive()),
                                new MapPoint.DataModel("Flammable", stock.getFlammable())
                        );
                        this.getMapPoint().getPortTable().setItems(portDescription);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            // new CreateCargoListWindow(this.primaryStage, shipsQuque.size(), this);
        });
        monitoring.start();
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
        return String.valueOf(portName);
    }

    public PortWindow getPortWindow() {
        return portWindow;
    }

    @Override
    public void run() {
        ShipAction shipAction = new ShipAction();
        //  while(true) {
        System.out.println(String.valueOf(this.portName));
        shipsQuque = shipAction.GetShipsFromCurrentPort(String.valueOf(this.portName));
        if (!shipsQuque.isEmpty()) {
            // PortWindow portWindow=new PortWindow(primaryStage);
            for (Ship ship : shipsQuque) {
                ship.setGetFromStock(getFromStockQuque);
                ship.setPutIntoStock(putIntoStockQuque);
                ship.setDockSemaphore(portSemaphore);
                ship.SetShipAction(shipAction);
                ship.setPortWindow(portWindow);
                Thread shipThread = new Thread(ship);
                shipThread.start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //   }
    }
}
