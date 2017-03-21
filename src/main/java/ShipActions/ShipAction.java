package ShipActions;

import ShipDescription.Ship;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Created by Андрей on 20.03.2017.
 */
public class ShipAction extends SuperExtd  implements  ShipActionInterface{
    @Override
    public PriorityQueue<Ship> GetShipsFromCurrentPort(String portName) {
        PriorityQueue<Ship> ships=new PriorityQueue<>();
        String query = "Select * from ships WHERE portName=?";
        PreparedStatement statement;
        try {
            // System.out.println(GetStatement().isClosed())
            statement = GetConnection().prepareStatement(query);
            statement.setString(1, portName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Ship ship=new Ship(resultSet.getString("shipName"));
                ship.setShipPrioity(resultSet.getInt("shipPriority"));
                ships.add(ship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
    }
}
