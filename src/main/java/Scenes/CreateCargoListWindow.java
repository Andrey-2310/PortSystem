package Scenes;

import CargoDescription.Cargo;
import CargoDescription.CargoAction.CargoAction;
import PortDescription.Port;
import ShipDescription.ShipActions.ShipAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by Андрей on 23.03.2017.
 */
public class CreateCargoListWindow {
    public CreateCargoListWindow(Stage cargoListStage, int amountOfCargos, Port port) {
        System.out.println(amountOfCargos);
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        Label createLabel = new Label("Create cargo list");
        createLabel.setStyle("-fx-font-size: 15px; -fx-font-family: Castellar;");
        borderPane.setTop(createLabel);
        BorderPane.setAlignment(createLabel, Pos.CENTER);
        createLabel.setMinHeight(70);
        borderPane.setCenter(gridPane);
        gridPane.setMaxWidth(600);
        gridPane.setVgap(15);
        gridPane.setHgap(30);
        gridPane.setPadding(new Insets(0, 10, 0, 70));
        Label[] cargoTypesRow0 = new Label[]{new Label("Ordinary"), new Label("Poisonous"), new Label("Flammable")};
        Label[] cargoTypesRow2 = new Label[]{new Label("Explosive"), new Label("Sensitive")};
        for (Label label: cargoTypesRow0) {
            label.setStyle("-fx-text-fill: brown; -fx-font-size: 15px; -font-width: Bold;");
        }
        for (Label label : cargoTypesRow2) {
            label.setStyle("-fx-text-fill: brown; -fx-font-size: 15px; -font-width: Bold;");
        }

        ObservableList<String> destinationList = FXCollections.observableArrayList(
                "Huoston", "Rotterdam", "Shanhai", "Dubai", "Richards Bay", "San Luis", "Port Hedland");
        ComboBox destinationBox = new ComboBox(destinationList);
        gridPane.add(destinationBox, 1, 0);

        Label priority = new Label("Priority");
        priority.setStyle("-fx-text-fill: darkgreen; -fx-font-size: 15px; -font-width: Bold;");
        gridPane.add(priority, 2, 0);

        Label destination = new Label("Destination");
        destination.setStyle("-fx-text-fill: darkgreen; -fx-font-size: 15px; -font-width: Bold;");
        gridPane.add(destination, 0, 0);

        ObservableList<String> priorityList = FXCollections.observableArrayList("3", "2", "1");
        ComboBox priorityBox = new ComboBox(priorityList);
        priorityBox.setMinWidth(120);
        gridPane.add(priorityBox, 3, 0);

        Label instructionLabel = new Label("Instruction");
        instructionLabel.setStyle("-fx-text-fill: gold; -fx-font-size: 15px; -font-width: Bold;");
        gridPane.add(instructionLabel, 2, 3);
        TextField[] textFields = new TextField[6];
        for (int i = 0; i < 6; i++) {
            textFields[i] = new TextField();
            if (i < 3)
                gridPane.add(textFields[i], 1, i + 1);
            else
                gridPane.add(textFields[i], 3, i - 2);
            textFields[i].setMaxWidth(120);
        }
        for (TextField textField : textFields)
            textField.setOnMousePressed(event -> textField.clear());

        //gridPane.add(textFields[5], 3, 1);
        for (int i = 0; i < 3; i++)
            gridPane.add(cargoTypesRow0[i], 0, i + 1);
        for (int i = 0; i < 2; i++)
            gridPane.add(cargoTypesRow2[i], 2, i + 1);

        Button auto = new Button("Auto");
        auto.setMinWidth(80);
        gridPane.add(auto, 2, 4);

        Button next = new Button("Next");
        if (amountOfCargos == 1)
            next.setText("Complete");
        next.setMinWidth(120);
        gridPane.add(next, 1, 4);
        next.setOnMouseClicked(event -> {

            boolean incorrectFields = false;
            try {
                if (destinationBox.getSelectionModel().getSelectedItem().toString().equals(Port.getPortName())) {
                    System.out.println("Пункт назначения совпадает с данным портом");
                    incorrectFields = true;
                }

            } catch (NullPointerException npe) {
                System.out.println("Выберите пункт назначения");
                incorrectFields = true;
            }

            if (!incorrectFields)
                try {
                    priorityBox.getSelectionModel().getSelectedItem().toString();
                } catch (NullPointerException npe) {
                    System.out.println("Выберите приоритет груза");
                    incorrectFields = true;
                }

            if (!incorrectFields)
                for (int i = 0; i < 5; i++) {
                    if (textFields[i].getText().isEmpty()) {
                        System.out.println("Поле " + i + " пустое");
                        incorrectFields = true;
                        break;
                    }
                    if (!textFields[i].getText().matches("^-?\\d+$")) {
                        System.out.println("Поле " + i + " не является числом");
                        incorrectFields = true;
                        break;
                    }
                    if (Integer.parseInt(textFields[i].getText()) < 0) {
                        System.out.println("Количество груза в поле " + i + "меньше нуля");
                        incorrectFields = true;
                        break;
                    }
                    if (Integer.parseInt(textFields[i].getText()) > 100) {
                        System.out.println("Количество груза в поле " + i + "больше допустимой нормы в 100 контейнеров");
                        incorrectFields = true;
                        break;
                    }
                }

            if (!incorrectFields) {

                CargoAction cargoAction = new CargoAction();
                cargoAction.InsertCargo(new Cargo(Port.getPortName(),
                        destinationBox.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(priorityBox.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(textFields[0].getText()), Integer.parseInt(textFields[3].getText()),
                        Integer.parseInt(textFields[1].getText()), Integer.parseInt(textFields[4].getText()),
                        Integer.parseInt(textFields[2].getText()), textFields[5].getText()));
                if (amountOfCargos > 1) {
                    new CreateCargoListWindow(cargoListStage, amountOfCargos - 1, port);
                } else{
                   // new PortWindow(cargoListStage);
                    Thread portThread=new Thread(port);
                    portThread.start();
                }
            }
        });
        auto.setOnMousePressed(event -> {
            Random random = new Random();
            for (int i = 0; i < 5; i++)
                textFields[i].setText(String.valueOf(random.nextInt(101)));
        });
        Scene cargoListScene = new Scene(borderPane, 600, 350);
        cargoListStage.setScene(cargoListScene);
    }
}
