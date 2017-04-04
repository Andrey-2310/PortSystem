package ShipDescription.ShipActions;

import ShipDescription.Ship;
import sample.SuperExtd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Андрей on 20.03.2017.
 */
public class ShipAction extends SuperExtd implements ShipActionInterface {
    @Override
    public PriorityBlockingQueue<Ship> GetShipsFromCurrentPort(String portName) {
        PriorityBlockingQueue<Ship> ships = new PriorityBlockingQueue<>();
        String query1 = "SELECT ID FROM ports WHERE portName=?";
        String query2 = "SELECT * FROM ships WHERE portID=?";
        PreparedStatement statement1;
        PreparedStatement statement2;
        try {
            statement1 = GetConnection().prepareStatement(query1);
            statement1.setString(1,portName);
            ResultSet resultSet1=statement1.executeQuery();
            resultSet1.next();
            statement2 = GetConnection().prepareStatement(query2);
            statement2.setInt(1, resultSet1.getInt(1));
            ResultSet resultSet = statement2.executeQuery();
            while (resultSet.next()) {
                Ship ship = new Ship(resultSet.getString("shipName"));
                ship.setPriority(resultSet.getInt("shipPriority"));
                ships.add(ship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
    }

    @Override
    public synchronized int WhatDockIsEmpty(String shipName) {
        String query1 = "SELECT portID FROM ships WHERE shipName=?;";
        String query2 = "SELECT dock1 FROM ports WHERE ID=?;";
        try {
            PreparedStatement preparedStatement1 = GetConnection().prepareStatement(query1);
            preparedStatement1.setString(1, shipName);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            // System.out.println(resultSet1.getInt("portID"));
            PreparedStatement preparedStatement2 = GetConnection().prepareStatement(query2);
            preparedStatement2.setInt(1, resultSet1.getInt("portID"));
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            resultSet2.next();
            return resultSet2.getInt("dock1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public synchronized void FulFillDoc(int numberOfDock) {
        try {
            if (numberOfDock == 1) {
                String query = "UPDATE  ports SET dock1=1;";
                Statement preparedStatement = GetConnection().createStatement();
                preparedStatement.executeUpdate(query);

            } else {
                String query = "UPDATE  ports SET dock2=1;";
                Statement preparedStatement = GetConnection().createStatement();
                preparedStatement.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ReleaseDock(int numberOfDock) {
        try {
            if (numberOfDock == 1) {
                String query = "UPDATE  ports SET dock1=0;";
                Statement preparedStatement = GetConnection().createStatement();
                preparedStatement.executeUpdate(query);

            } else {
                String query = "UPDATE  ports SET dock2=0;";
                Statement preparedStatement = GetConnection().createStatement();
                preparedStatement.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
