package StockDescription.StockAction;

import StockDescription.Stock;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Андрей on 01.04.2017.
 */
public class StockActionDB  extends SuperExtd implements StockActionInterface {
    @Override
    public Stock GetStockState( String portName) {
        String query1="SELECT ID from portdispatchsystem.ports WHERE portName=?";
        String query2="Select * from portdispatchsystem.stock where portID=?";
        try {
            PreparedStatement preparedStatement1 = GetConnection().prepareStatement(query1);
            preparedStatement1.setString(1, portName);
            ResultSet resultSet1=preparedStatement1.executeQuery();
            resultSet1.next();
            PreparedStatement preparedStatement2=GetConnection().prepareStatement(query2);
            preparedStatement2.setInt(1, resultSet1.getInt(1));
            ResultSet resultSet2=preparedStatement2.executeQuery();
            resultSet2.next();
            return new Stock(resultSet2.getInt(3), resultSet2.getInt(4), resultSet2.getInt(5),
                    resultSet2.getInt(6), resultSet2.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
