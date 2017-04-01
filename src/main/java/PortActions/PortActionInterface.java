package PortActions;

import PortDescription.Port;
import javafx.stage.Stage;

import java.util.Vector;

/**
 * Created by Андрей on 19.03.2017.
 */
public interface PortActionInterface{
    Vector<Port> GenerateAllPorts( Stage primaryStage);
}
