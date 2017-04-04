package PortDescription;

import CargoDescription.Cargo;
import Scenes.Map.MapPoint;
import ShipDescription.Ship;
import ShipDescription.ShipActions.ShipAction;
import StockDescription.Stock;
import StockDescription.StockAction.StockActionDB;
import StockDescription.StockAction.StockActionInterface;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;

import java.util.Vector;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
/**
 * Created by Андрей on 16.03.2017.
 */
public class Port implements Runnable {
    private static final Logger log = Logger.getLogger(Port.class);
    private String portName;
    private Point2D coord;
    private Vector<Dock> docks;
    private MapPoint mapPoint;
    private Stock stock;
    private PriorityBlockingQueue<Cargo> putIntoStockQuque, getFromStockQuque;
    private PriorityBlockingQueue<Ship> shipsQuque;
    private Semaphore portSemaphore;
    //private Stage primaryStage;

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
        StockActionInterface stockAction = new StockActionDB();
        stock = stockAction.GetStockState(portName);
        stock.setGetFromStockQuque(getFromStockQuque);
        stock.setPutIntoStockQuque(putIntoStockQuque);
        stock.getGetFromStockThread().start();
        stock.getPutIntoStockThread().start();
       // Thread monitoring = new Thread(() -> {
//            while (true)
//                try {
//                    Thread.currentThread().sleep(5000);
//                    Platform.runLater(() -> {
//                        log.info("Произошло изменение в таблице для порта "+ portName);
//                       // System.out.println("Произошло изменение в таблице для порта"+ portName);
//                        ObservableList<MapPoint.DataModel> portDescription = FXCollections.observableArrayList(
//                                new MapPoint.DataModel("Quque length", portSemaphore.getQueueLength()),
//                                new MapPoint.DataModel("Ships in port", 2-portSemaphore.availablePermits()),
//                                new MapPoint.DataModel("Ordinary", stock.getOrdinary()),
//                                new MapPoint.DataModel("Explosive", stock.getExplosive()),
//                                new MapPoint.DataModel("Poisonous", stock.getPoisonous()),
//                                new MapPoint.DataModel("Sensetive", stock.getSensetive()),
//                                new MapPoint.DataModel("Flammable", stock.getFlammable())
//                        );
//                        this.getMapPoint().getPortTable().setItems(portDescription);
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            // new CreateCargoListWindow(this.primaryStage, shipsQuque.size(), this);
      //  });
      //  monitoring.start();
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

    public PriorityBlockingQueue<Ship> getShipsQuque() {
        return shipsQuque;
    }

    public Semaphore getPortSemaphore() {
        return portSemaphore;
    }

    public Stock getStock() {
        return stock;
    }

    public PriorityBlockingQueue<Cargo> getPutIntoStockQuque() {
        return putIntoStockQuque;
    }

    public PriorityBlockingQueue<Cargo> getGetFromStockQuque() {
        return getFromStockQuque;
    }

    @Override
    public void run() {
        System.out.println(String.valueOf(this.portName));
        ShipAction shipAction = new ShipAction();
        shipsQuque = shipAction.GetShipsFromCurrentPort(String.valueOf(this.portName));
        while (true) {
            if (!shipsQuque.isEmpty()) {
               // if(portSemaphore.availablePermits()!=0) {
                    Ship ship = shipsQuque.poll();
                    if(ship.getState()== Thread.State.NEW) {
                        ship.setPort(this);
                        ship.start();
                       /* try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
              //  }
            }


        }
    }
}
