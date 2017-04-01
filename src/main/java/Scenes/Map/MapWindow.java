package Scenes.Map;

import PortActions.PortAction;
import PortDescription.Port;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Vector;

/**
 * Created by Андрей on 17.03.2017.
 */
public class MapWindow {
    volatile Vector<Port> ports;

    public MapWindow(Stage mapStage) throws InterruptedException {
        Thread getPortsThread = new Thread(() -> {
            PortAction portAction = new PortAction();
            ports = portAction.GenerateAllPorts(mapStage);
        });
        getPortsThread.start();
        BorderPane borderPane = new BorderPane();
        HBox menu = new HBox();
        menu.setMinHeight(30);
        borderPane.setTop(menu);
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        ImageView mapImage = new ImageView(new Image("1fizicheskaja-karta-mira14.jpg"));
        pane.getChildren().add(mapImage);
        getPortsThread.join();
        for (Port port : ports) {
            pane.getChildren().add(port.getMapPoint().getPoint());
            port.getMapPoint().getPoint().setX(port.getCoord().getX());
            port.getMapPoint().getPoint().setY(port.getCoord().getY());

            pane.getChildren().add(port.getMapPoint().getPointName());
            port.getMapPoint().getPointName().setLayoutX(port.getCoord().getX() + 20);
            port.getMapPoint().getPointName().setLayoutY(port.getCoord().getY() - 10);

            pane.getChildren().add(port.getMapPoint().getPortTable());
            port.getMapPoint().getPortTable().setLayoutX(port.getCoord().getX()-150);
            port.getMapPoint().getPortTable().setLayoutY(port.getCoord().getY()-100);

        }

        mapStage.setScene(new Scene(borderPane, 1366, 718));
        mapStage.show();


        Thread startPortThreads = new Thread(() -> {
            for(Port port:ports) {
                Thread portThread = new Thread(port);
                portThread.start();
            }
        });
        startPortThreads.start();
    }

   public static synchronized void RefreshWindow(){}
}

