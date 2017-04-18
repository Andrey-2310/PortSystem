package PortDescription.PortActions;

import PortDescription.Port;
import javafx.geometry.Point2D;
import sample.SuperExtd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Андрей on 19.03.2017.
 */


/**This class operates with DB to organize PortActions*/
public class PortAction extends SuperExtd  {
    /**
     *This method generates all Port objects
     * @return all Port objects
     */
    public static void GenerateAllPorts() {
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


        Port.setPorts(ports);
    }
    /**
     * This method clears all docks of the port in DB
     */
    public static void ClearAllPorts() {
        String query="UPDATE  ports set dock1=0 and dock2=0;";
        Statement statement;
        try {
            statement = GetConnection().createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method randomly chooses next Port Ship will go
     * @param portName - name of current port
     * @return
     */
    public static Port ChooseNextPort(String portName) {
        Random random = new Random();
        Port nextPort;
        do {
            nextPort = Port.getPorts().get(random.nextInt(Port.getPorts().size()));
        } while (nextPort.getPortName().equals(portName));
        return nextPort;
    }

}
