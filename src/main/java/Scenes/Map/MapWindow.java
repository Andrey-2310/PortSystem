package Scenes.Map;

import PortDescription.Port;
import PortDescription.PortActions.PortAction;
import ShipDescription.Ship;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Андрей on 17.03.2017.
 */

/**
 * Class which describes current situation of ports
 */
public class MapWindow {

    /**Logger*/
    private static final Logger log = Logger.getLogger(Port.class);
    /**for setting current time*/
private final Calendar calendar=Calendar.getInstance();

    /**
     * Creates Map Window
     * Creates Port objects
     * Starts Port threads
     * Monitors Current Situation
     * @param mapStage - Stage of the app
     * @throws InterruptedException
     */
    public void CreatingMapWindow(Stage mapStage) throws InterruptedException {
        Thread getPortsThread = new Thread(() -> {
            PortAction.ClearAllPorts();
            PortAction.GenerateAllPorts();
        });
        getPortsThread.start();
        BorderPane borderPane = new BorderPane();
        HBox menu = new HBox();
        menu.setMinHeight(30);
        borderPane.setTop(menu);
        Button addShip=new Button("Add Ship");
        addShip.setStyle("-fx-background-color: green; -fx-font-size: 15px");
        addShip.setMinSize(683, 30);
        Button delShip=new Button("Delete Ship");
        delShip.setStyle("-fx-background-color: darkred; -fx-font-size: 15px");
        delShip.setMinSize(683, 30);
        menu.getChildren().addAll(addShip, delShip);
        addShip.setOnMousePressed(event -> {
           Port.getPorts().get(new Random().nextInt(Port.getPorts().size())).getShipsQuque().put(new Ship());
        });
        delShip.setOnMousePressed(event ->{
               Ship.getShips().remove(Ship.getShips().size()-1);
        });
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        ImageView mapImage = new ImageView(new Image("1fizicheskaja-karta-mira14.jpg"));
        pane.getChildren().add(mapImage);
        getPortsThread.join();
        for (Port port : Port.getPorts()) {
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

        Thread startPortThreads = new Thread(() -> {
            for (Port port : Port.getPorts()) {
                Thread portThread = new Thread(port);
                portThread.start();
            }
        });
        startPortThreads.start();
        Thread monitoring = new Thread(() -> {
            while (true) {

                for (Port port : Port.getPorts())
                    port.AddToPortFile("\nCurrent Time: " + new Timestamp(System.currentTimeMillis()) + "\nQuque length: " + port.getPortSemaphore().getQueueLength()
                            + "\nShips in port: " + (2 - port.getPortSemaphore().availablePermits()) + "\nOrdinary: " + port.getStock().getOrdinary() +
                            "\nExplosive: " + port.getStock().getExplosive() + "\nPoisonous" + port.getStock().getPoisonous() +
                            "\nSensetive: " + port.getStock().getSensetive() + "\nFlammable: " + port.getStock().getFlammable());
                Platform.runLater(() -> {
                    for (Port port : Port.getPorts()) {

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
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monitoring.start();

        Scene mapWindowScene = new Scene(borderPane, 1366, 718);
        if (mapStage.getScene() == null) {
            mapStage.setScene(mapWindowScene);
            mapStage.show();
        }
    }

}

