package PortActions;

import PortDescription.Port;
import javafx.geometry.Point2D;
import sample.SuperExtd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 * Created by Андрей on 19.03.2017.
 */
public class PortAction extends SuperExtd implements  PortActionInterface {
    @Override
    public Vector<Port> GenerateAllPorts() {
        Vector<Port> ports = new Vector<>();
        String query = "Select * from ports";

        // System.out.println(GetStatement().isClosed())
        Statement statement = null;
        try {
            statement = GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ports.addElement(new Port(resultSet.getString("portname"),
                      new Point2D(resultSet.getInt("portX"), resultSet.getInt("portY"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // Timestamp sqlTimestamp = new Timestamp(System.currentTimeMillis());

        return ports;
    }
}
