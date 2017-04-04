package Scenes.Map;

import PortDescription.Port;
import PortDescription.PortActions.PortAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Андрей on 17.03.2017.
 */
public class MapWindow {
    public static volatile Vector<Port> ports;
    private static final Logger log = Logger.getLogger(Port.class);

    public MapWindow(Stage mapStage) throws InterruptedException {
        Thread getPortsThread = new Thread(() -> {
            PortAction portAction = new PortAction();
            portAction.ClearAllPorts();
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
            port.getMapPoint().getPortTable().setLayoutX(port.getCoord().getX() - 150);
            port.getMapPoint().getPortTable().setLayoutY(port.getCoord().getY() - 100);

        }

        mapStage.setScene(new Scene(borderPane, 1366, 718));
        mapStage.show();


        Thread startPortThreads = new Thread(() -> {
            for (Port port : ports) {
                Thread portThread = new Thread(port);
                portThread.start();
            }
        });
        startPortThreads.start();
        Thread monitoring = new Thread(() -> {
            while (true)
                try {
                    Thread.currentThread().sleep(5000);
                    Platform.runLater(() -> {
                        for (Port port : ports) {
                            log.info("Произошло изменение в таблице для порта " + port.getPortName());
                            // System.out.println("Произошло изменение в таблице для порта"+ portName);
                            ObservableList<MapPoint.DataModel> portDescription = FXCollections.observableArrayList(
                                    new MapPoint.DataModel("Quque length", port.getPortSemaphore().getQueueLength()),
                                    new MapPoint.DataModel("Ships in port", 2 - port.getPortSemaphore().availablePermits()),
                                    new MapPoint.DataModel("Ordinary", port.getStock().getOrdinary()),
                                    new MapPoint.DataModel("Explosive", port.getStock().getExplosive()),
                                    new MapPoint.DataModel("Poisonous", port.getStock().getPoisonous()),
                                    new MapPoint.DataModel("Sensetive", port.getStock().getSensetive()),
                                    new MapPoint.DataModel("Flammable", port.getStock().getFlammable()));
                            port.getMapPoint().getPortTable().setItems(portDescription);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        monitoring.start();
    }

    public static Port ChooseNextPort(String portName) {
        Random random = new Random();
        Port nextPort;
        do {
            nextPort = ports.get(random.nextInt(ports.size()));
        } while (nextPort.getPortName().equals(portName));
        return nextPort;
    }
}

