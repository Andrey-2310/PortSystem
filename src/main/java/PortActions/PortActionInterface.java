package PortActions;

import PortDescription.Port;

import java.util.Vector;

/**
 * Created by Андрей on 19.03.2017.
 */
public interface PortActionInterface{
    Vector<Port> GenerateAllPorts();
    Vector<Thread> ConstuctAllPortThreads(Vector<Port> ports);
    void StartAllPortThreads(Vector<Thread> portThreads);
}
