package CargoDescription.CargoAction;

import CargoDescription.Cargo;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Андрей on 28.03.2017.
 */
public class CargoAction extends SuperExtd implements CargoActionInterface {

    @Override
    public void InsertCargo(Cargo cargo) {
        String query = "INSERT INTO cargo (priority, shipID, ordinary, explosive, " +
                "poisonous, sensetive, flammable, instruction) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = GetConnection().prepareStatement(query);
            preparedStatement.setInt(1, cargo.getPriority());
            preparedStatement.setInt(2, cargo.getShipID());
            preparedStatement.setInt(3, cargo.getOrdinary());
            preparedStatement.setInt(4, cargo.getExplosive());
            preparedStatement.setInt(5, cargo.getPoisonous());
            preparedStatement.setInt(6, cargo.getSensetive());
            preparedStatement.setInt(7, cargo.getFlammable());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean CheckAnyCargo() {
        String query = "SELECT  * FROM cargo";
        try {
            Statement statement = GetConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Cargo GetCargoOfShip(String shipName) {
        String query1 = "SELECT ID FROM ships WHERE shipName=?;";
        String query2 = "SELECT * FROM cargo WHERE shipID=?;";
        try {
            PreparedStatement preparedStatement1 = GetConnection().prepareStatement(query1);
            preparedStatement1.setString(1, shipName);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            PreparedStatement preparedStatement2 = GetConnection().prepareStatement(query2);
            preparedStatement2.setInt(1, resultSet1.getInt(1));
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            return new Cargo(resultSet2.getInt("priority"), resultSet2.getInt("shipID"),
                    resultSet2.getInt("ordinary"),
                    resultSet2.getInt("explosive"), resultSet2.getInt("poisonous"),
                    resultSet2.getInt("sensetive"), resultSet2.getInt("flammable"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
