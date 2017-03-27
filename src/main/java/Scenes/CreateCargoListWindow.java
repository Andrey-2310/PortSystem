package Scenes;

import com.mysql.cj.api.x.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        GridPane gridPane = new GridPane();
        Label createLabel = new Label("Create cargo list");
        borderPane.setTop(createLabel);
        BorderPane.setAlignment(createLabel, Pos.CENTER);
        createLabel.setMinHeight(50);
        borderPane.setCenter(gridPane);
        gridPane.setMaxWidth(400);
        gridPane.setVgap(15);
        gridPane.setHgap(30);
        gridPane.setPadding(new Insets(0, 30, 0, 70));
        Label[] cargoTypes = new Label[]{new Label("Destination"), new Label("Ordinary"), new Label("Poisonous"), new Label("Flammable"),
                new Label("Explosive"), new Label("Sensitive"), new Label("Priority")};
        for (int i = 0; i < 5; i++)
            cargoTypes[i+1].setStyle("-fx-text-fill: brown; -fx-font-size: 15px; -font-width: Bold;");
        ObservableList<String> destinationList = FXCollections.observableArrayList(
                "Huoston", "Rotterdam", "Shanhai", "Dubai", "Richards Bay", "San Luis", "Port Hedland");
        ComboBox comboBox = new ComboBox(destinationList);
        gridPane.add(comboBox, 1, 0);
        TextField[] textFields = new TextField[6];
        for (int i = 0; i < 6; i++) {
            textFields[i] = new TextField();
            gridPane.add(textFields[i], 1, i + 1);
        }

        gridPane.addColumn(0, cargoTypes);
        //gridPane.setStyle("-fx-background-color: blue; -fx-font-size: 15");
        Scene cargoListScene = new Scene(borderPane, 400, 350);
        cargoListStage.setScene(cargoListScene);
    }
}
