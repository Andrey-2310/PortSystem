package PortDescription;

import javafx.geometry.Point2D;
import Scenes.MapPoint;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Port {
    private String portName;
    private int dockAmount;
    Point2D coord;
    private Vector<Dock> docks;
    MapPoint mapPoint;


    public Port(String portName, Point2D coord) {
        this.portName = portName;
        Random random = new Random();
        this.dockAmount = random.nextInt(20) + 1;
        this.docks = new Vector<>(this.dockAmount);
        this.coord = coord;
        mapPoint = new MapPoint(new Image("маячок.png"));
        mapPoint.getPointName().setText(this.portName);

        mapPoint.getPoint().setOnMouseEntered(event ->
                mapPoint.getPointName().setVisible(true));

        mapPoint.getPoint().setOnMouseExited(event ->
                mapPoint.getPointName().setVisible(false));
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
}
