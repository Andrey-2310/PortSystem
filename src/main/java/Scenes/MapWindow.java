package Scenes;

import PortDescription.Port;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Vector;

/**
 * Created by Андрей on 17.03.2017.
 */
public class MapWindow {
    public MapWindow(Stage mapStage, Vector<Port> ports){
        mapStage.setTitle("Hello World");
        BorderPane borderPane=new BorderPane();
        MenuBar menu=new MenuBar();
        menu.setMinHeight(30);
        borderPane.setTop(menu);
        Pane pane=new Pane();
        borderPane.setCenter(pane);
        ImageView mapImage=new ImageView(new Image("fizicheskaja-karta-mira14.jpg"));
        pane.getChildren().add(mapImage);

        for(Port port: ports){
            pane.getChildren().add(port.getMapPoint().getPoint());
            port.getMapPoint().getPoint().setX(port.getCoord().getX());
            port.getMapPoint().getPoint().setY(port.getCoord().getY());

            pane.getChildren().add(port.getMapPoint().getPointName());
            port.getMapPoint().getPointName().setLayoutX(port.getCoord().getX()+20);
            port.getMapPoint().getPointName().setLayoutY(port.getCoord().getY()-10);

        }
     /*   for(int i=0; i<5; i++){
            mapPoints.add(new MapPoint(new Image("маячок.png")));
        }*/
      //  pane.getChildren().addAll(mapPoints);
//        mapPoints.get(0).setX(100);

        mapStage.setScene(new Scene(borderPane, 850, 450));

    }
}
