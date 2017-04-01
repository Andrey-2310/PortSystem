package StockDescription;

/**
 * Created by Андрей on 19.03.2017.
 */
public class Stock {
    private int sensetive;
    private int ordinary;
    private int poisonous;
    private int explosive;
    private int flammable;

    public Stock(int ordinary, int explosive, int poisonous, int sensetive, int flammable) {
        this.sensetive = sensetive;
        this.ordinary = ordinary;
        this.poisonous = poisonous;
        this.explosive = explosive;
        this.flammable = flammable;
    }

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
