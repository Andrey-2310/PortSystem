package PortDescription.PortActions;

import PortDescription.Port;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import static sample.SuperExtd.GetConnection;


public class PortActionTest {
    @Test
    public void generateAllPorts() throws Exception {
        PortAction.GenerateAllPorts();
        Assert.assertNotEquals(0, Port.getPorts().size());
    }

    @Test
    public void clearAllPorts() throws Exception {
        PortAction.ClearAllPorts();
        Vector<Port> ports = new Vector<>();
        String query = "SELECT * FROM ports where dock1=0 and dock2=0;";

        // System.out.println(GetStatement().isClosed())
        Statement statement;
        try {
            statement = GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                ports.addElement(new Port(resultSet.getString("portname"),
                        new Point2D(resultSet.getInt("portX"), resultSet.getInt("portY"))));
            }
            PortAction.GenerateAllPorts();

            Assert.assertEquals(Port.getPorts(), ports.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void chooseNextPort() throws Exception {
        Random random = new Random();
        PortAction.GenerateAllPorts();
        Port port = Port.getPorts().get(random.nextInt(Port.getPorts().size()));
        Port nextPort = PortAction.ChooseNextPort(port.getPortName());
        Assert.assertTrue(Port.getPorts().contains(nextPort));
    }

}