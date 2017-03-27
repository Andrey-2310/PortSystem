package PortActions;

import PortDescription.Port;
import javafx.geometry.Point2D;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by Андрей on 19.03.2017.
 */
public class PortAction extends SuperExtd implements PortActionInterface {
    @Override
    public Vector<Port> GenerateAllPorts() {
        Vector<Port> ports = new Vector<>();
        String query = "SELECT * FROM ports";

        // System.out.println(GetStatement().isClosed())
        Statement statement;
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

    @Override
    public Vector<Thread> ConstuctAllPortThreads(Vector<Port> ports) {
        Vector<Thread> portThreads = new Vector<>();
        for (Port port : ports) {
            portThreads.add(new Thread(port));
        }
        return portThreads;
    }

    @Override
    public void StartAllPortThreads(Vector<Thread> portThreads) {
        for (Thread portThread : portThreads)
            portThread.start();
    }



}
