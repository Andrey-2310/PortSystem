package Client;

import java.util.Vector;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Ship implements Runnable {
    private String  shipName;
    private String route;
    private Vector<Cargo> cargo;
    private Thread thread;

    public Ship(String shipName) {
        this.shipName = shipName;
        thread=new Thread( this, this.shipName);
    }
    public void ShipStart(){
        this.thread.start();
    }

    @Override
    public void run() {

    }
}
