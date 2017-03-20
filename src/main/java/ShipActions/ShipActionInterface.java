package ShipActions;

import ShipDescription.Ship;

import java.util.Vector;

/**
 * Created by Андрей on 20.03.2017.
 */
public interface ShipActionInterface {
    Vector<Ship> GetShipsFromCurrentDock(String portName, int numberOfDock);
}
