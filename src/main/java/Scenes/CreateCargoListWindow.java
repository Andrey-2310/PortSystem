package Scenes;

import com.mysql.cj.api.x.Table;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Андрей on 23.03.2017.
 */
public class CreateCargoListWindow {
    public CreateCargoListWindow(Stage cargoListStage) {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane=new GridPane();
        Label createLabel = new Label("Create cargo list");
        borderPane.setTop(createLabel);
        BorderPane.setAlignment(createLabel, Pos.CENTER);
        borderPane.setCenter(gridPane);
        Text[] cargoTypes=new Text[]{new Text("General"), new Text("Poisonous"), new Text("Sensitive")};
        gridPane.addColumn(0,cargoTypes);

        Scene cargoListScene=new Scene(borderPane, 600, 450);
        cargoListStage.setScene(cargoListScene);
    }
}
