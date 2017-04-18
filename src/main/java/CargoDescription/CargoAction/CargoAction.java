package CargoDescription.CargoAction;

import CargoDescription.Cargo;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Андрей on 28.03.2017.
 */
public class CargoAction extends SuperExtd  {

    /**
     This method gets start cargo of the ship by it's name from DB
     * @param shipName
     * @return Cargo of the ship
     */

    public  static Cargo GetCargoOfShip(String shipName) {
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

    /**
     * Generates random Cargo
     * @return Cargo
     */
    public static Cargo GenerateCargo(){
        Random random=new Random();
        return new Cargo(random.nextInt(100)+1, 0,  random.nextInt(101),
                random.nextInt(101),  random.nextInt(101),  random.nextInt(101),  random.nextInt(101));
    }
}
