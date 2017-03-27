package ShipActions;

import ShipDescription.Ship;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Created by Андрей on 20.03.2017.
 */
public interface ShipActionInterface{
    PriorityQueue<Ship> GetShipsFromCurrentPort(String portName);
    int  WhatDockIsEmpty(String shipName);
    void FulFillDoc(int numberOfDock);
    void ReleaseDock(int numberOfDock);
}
