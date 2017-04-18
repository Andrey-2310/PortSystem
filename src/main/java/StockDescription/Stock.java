package StockDescription;

import CargoDescription.Cargo;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Андрей on 19.03.2017.
 */
public class Stock {
    /**Characteristics of Stock */
    private volatile int sensetive;
    private volatile int ordinary;
    private volatile int poisonous;
    private volatile int explosive;
    private volatile int flammable;
    /**Threads to get and put Cargos to Stock*/
    private Thread putIntoStockThread, getFromStockThread;
    /**Queues to get and put Cargos to Stock*/
    private PriorityBlockingQueue<Cargo> putIntoStockQuque, getFromStockQuque;

    /**
     * Creating Stock object
     * @param ordinary
     * @param explosive
     * @param poisonous
     * @param sensetive
     * @param flammable
     */
    public Stock(int ordinary, int explosive, int poisonous, int sensetive, int flammable) {
        this.sensetive = sensetive;
        this.ordinary = ordinary;
        this.poisonous = poisonous;
        this.explosive = explosive;
        this.flammable = flammable;
        putIntoStockThread = new Thread(() -> {
            Cargo cargo;
            while (true) {
                if (!putIntoStockQuque.isEmpty()) {
                    cargo = putIntoStockQuque.poll();
                    this.addOrdinary(cargo.getOrdinary());
                    this.addExplosive(cargo.getExplosive());
                    this.addFlammable(cargo.getFlammable());
                    this.addPoisonous(cargo.getPoisonous());
                    this.addSensetive(cargo.getSensetive());
                }
                else try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        getFromStockThread = new Thread(() -> {
            Random random = new Random();
            Cargo cargo;
            while (true) {
                if(getFromStockQuque.size()<2) {
                    cargo = new Cargo(random.nextInt(3) + 1, 0, random.nextInt(101), random.nextInt(101),
                            random.nextInt(101), random.nextInt(101), random.nextInt(101));
                    getFromStockQuque.add(cargo);
                    this.subExplosive(cargo.getExplosive());
                    this.subFlammable(cargo.getFlammable());
                    this.subOrdinary(cargo.getOrdinary());
                    this.subPoisonous(cargo.getPoisonous());
                    this.subSensetive(cargo.getSensetive());
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    /**Getter for putIntoStockThread*/
    public Thread getPutIntoStockThread() {
        return putIntoStockThread;
    }
    /**Getter for getFromStockThread*/
    public Thread getGetFromStockThread() {
        return getFromStockThread;
    }
    /**Getter for putIntoStockQuque*/
    public void setPutIntoStockQuque(PriorityBlockingQueue<Cargo> putIntoStockQuque) {
        this.putIntoStockQuque = putIntoStockQuque;
    }
    /**Getter for getFromStockQuque*/
    public void setGetFromStockQuque(PriorityBlockingQueue<Cargo> getFromStockQuque) {
        this.getFromStockQuque = getFromStockQuque;
    }

    /**Getters for Stock params*/
    public synchronized int getSensetive() {
        return sensetive;
    }

    public synchronized int getOrdinary() {
        return ordinary;
    }

    public synchronized int getPoisonous() {
        return poisonous;
    }

    public synchronized int getExplosive() {
        return explosive;
    }

    public synchronized int getFlammable() {
        return flammable;
    }



/**Operations adding and substructing with Stock params*/
    public synchronized void subSensetive(int sensetive) {
        while (true)
            if (this.sensetive - sensetive < 0) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else break;
        this.sensetive -= sensetive;
        notify();
    }

    public synchronized void subOrdinary(int ordinary) {
        while (true)
            if (this.ordinary - ordinary < 0) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else break;
        this.ordinary -= ordinary;
        notify();
    }

    public synchronized void subPoisonous(int poisonous) {
        while (true)
            if (this.poisonous - poisonous < 0) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else break;
        this.poisonous -= poisonous;
        notify();
    }

    public synchronized void subExplosive(int explosive) {
        while (true)
            if (this.explosive - explosive < 0) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else break;
        this.explosive -= explosive;
        notify();
    }

    public synchronized void subFlammable(int flammable) {
        while (true)
            if (this.flammable - flammable < 0) try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            else break;
        this.flammable -= flammable;
        notify();
    }

    public synchronized void addOrdinary(int ordinary) {
        this.ordinary += ordinary;
        notify();
    }

    public synchronized void addExplosive(int explosive) {
        this.explosive += explosive;
        notify();
    }

    public synchronized void addPoisonous(int poisonous) {
        this.poisonous += poisonous;
        notify();
    }

    public synchronized void addSensetive(int sensetive) {
        this.sensetive += sensetive;
        notify();
    }

    public synchronized void addFlammable(int flammable) {
        this.flammable += flammable;
        notify();
    }

}
