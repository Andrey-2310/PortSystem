package ShipDescription.ShipActions;

import ShipDescription.Ship;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Андрей on 20.03.2017.
 */
public interface ShipActionInterface{
    PriorityBlockingQueue<Ship> GetShipsFromCurrentPort(String portName);
    int  WhatDockIsEmpty(String shipName);
    void FulFillDoc(int numberOfDock);
    void ReleaseDock(int numberOfDock);
}
