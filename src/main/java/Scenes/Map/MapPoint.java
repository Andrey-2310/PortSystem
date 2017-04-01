package Scenes.Map;

import com.mysql.cj.api.x.Table;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Created by Андрей on 18.03.2017.
 */
public class MapPoint {
    private ImageView point;
    private Label pointName;

    private TableView portTable;

    public MapPoint() {
        point = new ImageView(new Image("маячок.png"));
        point.setFitWidth(30);
        point.setFitHeight(30);
        pointName = new Label();
        pointName.setVisible(false);
        pointName.setStyle(" -fx-background-color: #f9e7a4; -fx-font-weight: bold; -fx-background-radius: 5;");

        portTable = new TableView();
        portTable.setPrefWidth(150);
        portTable.setPrefHeight(200);
        portTable.setVisible(true);
        TableColumn attribute = new TableColumn("Attribute");
        attribute.setPrefWidth(95);
        TableColumn amount = new TableColumn("Amount");
        amount.setPrefWidth(53);
        portTable.getColumns().addAll(attribute, amount);
        attribute.setCellValueFactory(new PropertyValueFactory<DataModel, String>("attribute"));
        amount.setCellValueFactory(new PropertyValueFactory<DataModel, Integer>("amount"));

    }

    public ImageView getPoint() {
        return point;
    }

    public Label getPointName() {
        return pointName;
    }

    public TableView getPortTable() {
        return portTable;
    }

   public static class DataModel {
        SimpleStringProperty attribute;
        SimpleIntegerProperty amount;

        public DataModel(String attribute, int amount) {
            this.attribute = new SimpleStringProperty(attribute);
            this.amount = new SimpleIntegerProperty(amount);
        }

       public String getAttribute() {
           return attribute.get();
       }

       public SimpleStringProperty attributeProperty() {
           return attribute;
       }

       public void setAttribute(String attribute) {
           this.attribute.set(attribute);
       }

       public int getAmount() {
           return amount.get();
       }

       public SimpleIntegerProperty amountProperty() {
           return amount;
       }

       public void setAmount(int amount) {
           this.amount.set(amount);
       }
   }
}
