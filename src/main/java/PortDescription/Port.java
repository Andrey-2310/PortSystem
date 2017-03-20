package PortDescription;

import ShipDescription.Cargo;
import javafx.geometry.Point2D;
import Scenes.MapPoint;
import javafx.scene.image.Image;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Port implements Runnable{
    private String portName;
    private Point2D coord;
    private Vector<Dock> docks;
    private MapPoint mapPoint;
    private Stock stock;
    private BlockingQueue<Cargo> putIntoStock, getFromStock;

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
            putIntoStock=new PriorityBlockingQueue<>();
            getFromStock=new PriorityBlockingQueue<>();
            this.docks = new Vector<>(3);
            for(int i=0; i<this.docks.capacity(); i++)
                this.docks.add(new Dock(this.portName, i, putIntoStock, getFromStock));
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
