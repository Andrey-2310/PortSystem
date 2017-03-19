package Scenes;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Андрей on 18.03.2017.
 */
public class MapPoint extends ImageView{
    private ImageView point;
    private Label pointName;

    public MapPoint(Image image){
        point=new ImageView(image);
        point.setFitWidth(30);
        point.setFitHeight(30);
        pointName=new Label();
        pointName.setVisible(false);
        pointName.setStyle(" -fx-background-color: #f9e7a4; -fx-font-weight: bold; -fx-background-radius: 5;");
      /*  point.setOnMouseClicked(event -> System.out.println("krasava"));
        point.setOnMouseEntered(event-> {point.setFitWidth(100);
        point.setFitWidth(100);
        });
        point.setOnMouseEntered(event-> {point.setFitWidth(30);
            point.setFitWidth(30);
        });*/
    }

    public ImageView getPoint() {
        return point;
    }

    public Label getPointName() {
        return pointName;
    }

}
