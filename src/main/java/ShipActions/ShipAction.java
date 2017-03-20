package ShipActions;

import ShipDescription.Ship;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by Андрей on 20.03.2017.
 */
public class ShipAction extends SuperExtd  implements  ShipActionInterface{
    @Override
    public Vector<Ship> GetShipsFromCurrentDock(String portName, int numberOfDock) {
        Vector<Ship> ships=new Vector<>();
        String query = "Select * from ships WHERE dockNum=? and portName=?";
        PreparedStatement statement;
        try {
            // System.out.println(GetStatement().isClosed())
            statement = GetConnection().prepareStatement(query);
            statement.setInt(1, numberOfDock);
            statement.setString(2, portName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                ships.add(new Ship(resultSet.getString("shipName")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
    }
}
