package ShipDescription;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Ship implements Runnable {
    private String shipName;
    private String route;
    private Vector<Cargo> cargo;
    private Semaphore dockSemaphore;
    private BlockingQueue<Cargo> putIntoStock, getFromStock;

    public void setDockSemaphore(Semaphore dockSemaphore) {
        this.dockSemaphore = dockSemaphore;
    }

    public void setPutIntoStock(BlockingQueue<Cargo> putIntoStock) {
        this.putIntoStock = putIntoStock;
    }

    public void setGetFromStock(BlockingQueue<Cargo> getFromStock) {
        this.getFromStock = getFromStock;
    }

    public Ship(String shipName) {
        this.shipName = shipName;
        System.out.println("Стартует корабль "+shipName);

    }

    @Override
    public void run() {

        try {
            System.out.println("Корабль "+ shipName +" Ожидает разрешения");
            dockSemaphore.acquire();
            System.out.println("Разрешение для корабля "+shipName +" получено");
            /**
             * обращение к причалу
             */
            Thread.sleep(1000);
            System.out.println("Корабль " +shipName+ " покидает порт");
            dockSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
