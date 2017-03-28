package CargoDescription.CargoAction;

import CargoDescription.Cargo;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Андрей on 28.03.2017.
 */
public class CargoAction extends SuperExtd implements CargoActionInterface {

    @Override
    public void InsertCargo(Cargo cargo) {
        String query="Insert into cargo (current, destination, priority, ordinary, explosive, " +
                "poisonous, sensetive, flammable, instruction) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = GetConnection().prepareStatement(query);
            preparedStatement.setString(1, cargo.getCurrent());
            preparedStatement.setString(2,cargo.getDestination());
            preparedStatement.setInt(3,cargo.getPriority());
            preparedStatement.setInt(4, cargo.getOrdinary());
            preparedStatement.setInt(5, cargo.getExplosive());
            preparedStatement.setInt(6, cargo.getPoisonous());
            preparedStatement.setInt(7, cargo.getSensetive());
            preparedStatement.setInt(8, cargo.getFlammable());
            preparedStatement.setString(9, cargo.getInstruction());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
