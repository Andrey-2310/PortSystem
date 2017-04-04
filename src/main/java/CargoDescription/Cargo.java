package CargoDescription;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Cargo implements Comparable<Cargo> {

    private int priority;
    private int shipID;
    private int sensetive;
    private int ordinary;
    private int poisonous;
    private int explosive;
    private int flammable;


    public int getSensetive() {return sensetive;}

    public int getOrdinary() {return ordinary;}

    public int getPoisonous() {return poisonous;}

    public int getExplosive() {return explosive;}

    public int getFlammable() {return flammable;}

    public int getShipID() {return shipID;}

    public Cargo(int priority,int shipID,  int ordinary, int explosive, int poisonous, int sensetive, int flammable) {
        this.priority = priority;
        this.sensetive = sensetive;
        this.ordinary = ordinary;
        this.poisonous = poisonous;
        this.explosive = explosive;
        this.flammable = flammable;
        this.shipID=shipID;
    }

    public int getPriority() {
        return priority;
    }

    Cargo(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Cargo anotherCargo) {
        if (this.priority > anotherCargo.getPriority()) return -1;
        if (this.priority < anotherCargo.getPriority()) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "priority=" + priority +
                ", shipID=" + shipID +
                ", sensetive=" + sensetive +
                ", ordinary=" + ordinary +
                ", poisonous=" + poisonous +
                ", explosive=" + explosive +
                ", flammable=" + flammable +
                '}';
    }
}
