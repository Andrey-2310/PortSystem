package Scenes;

import PortDescription.Dock;
import PortDescription.Port;
import ShipDescription.ShipDockConnector;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Андрей on 29.03.2017.
 */
public class PortWindow {

    Dock dock1, dock2;
    Stage primaryStage;
    Label[] dockLabels;
    TextField[] dockTextFields;

    Button[] dockOkButtons;

    public PortWindow(Stage primaryStage) {

        this.dock1 = new Dock(new ShipDockConnector(this), 1);
        this.dock2 = new Dock(new ShipDockConnector(this), 2);
        this.primaryStage = primaryStage;
    }

    public void CreatePortWindow() {
        BorderPane borderPane = new BorderPane();
        this.dockLabels = new Label[2];
        for(int i=0; i<dockLabels.length; i++ ) this.dockLabels[i] = new Label("Label"+i);
        this.dockOkButtons = new Button[2];
        for(int i=0; i<dockOkButtons.length; i++) this.dockOkButtons[i]=new Button("Ok"+i);
        this.dockTextFields = new TextField[2];
        for(int i=0; i<dockTextFields.length; i++ ) this.dockTextFields[i] = new TextField();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        hBox1.getChildren().addAll(dockLabels[0], dockTextFields[0], dockOkButtons[0]);
        hBox2.getChildren().addAll(dockLabels[1], dockTextFields[1], dockOkButtons[1]);
        borderPane.setTop(hBox1);
        borderPane.setBottom(hBox2);
        this.primaryStage.setScene(new Scene(borderPane, 600, 350));
    }

    public Label[] getDockLabels() {
        return dockLabels;
    }

    public TextField[] getDockTextFields() {
        return dockTextFields;
    }

    public Button[] getDockOkButtons() {
        return dockOkButtons;
    }

    public Dock GetDockByNumber(int number) {
        if (number == 1) {
           /* dock1.setDockLabel(docklabel1);
            dock1.setDockOkButton(dockOkButton1);
            dock1.setDockTextField(dockTextField1);*/
            return dock1;
        }
      /*  dock2.setDockLabel(docklabel2);
        dock2.setDockOkButton(dockOkButton2);
        dock2.setDockTextField(dockTextField2);*/
        return dock2;
    }

 /*   public int GetTimeFromUI(int numberOfDock){
        final int[] timeFromUI = new int[1];
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dockLabels[numberOfDock-1].setText("Введите время стоянки");
                dockOkButtons[numberOfDock-1].setOnMousePressed(event -> {
                    timeFromUI[0] = Integer.parseInt(dockTextFields[numberOfDock - 1].getText());
                });
            }
        });

        return timeFromUI[0];
    }*/
}
