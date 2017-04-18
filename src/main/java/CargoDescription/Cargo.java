package CargoDescription;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Cargo implements Comparable<Cargo> {

    /** priority of the cargo*/
    private int priority;
    /**ID of the ship*/
    private int shipID;
    /**amount of sensetive items*/
    private int sensetive;
    /**amount of ordinary items*/
    private int ordinary;
    /**amount of poisonous items*/
    private int poisonous;
    /**amount of explosive items*/
    private int explosive;
    /**amount of flammable items*/
    private int flammable;


    /**This method helps to get sensetive items in Cargo*/
    public int getSensetive() {return sensetive;}

    /**This method helps to get ordinary items in Cargo*/
    public int getOrdinary() {return ordinary;}

    /**This method helps to get poisonous items in Cargo*/
    public int getPoisonous() {return poisonous;}

    /**This method helps to get explosive items in Cargo*/
    public int getExplosive() {return explosive;}

    /**This method helps to get flammable items in Cargo*/
    public int getFlammable() {return flammable;}

    /**
     * Constructor of Cargo class
     * @param priority - priority of the cargo
     * @param shipID - ID of the ship
     * @param ordinary - amount of ordinary items
     * @param explosive - amount of explosive items
     * @param poisonous - amount of poisonous items
     * @param sensetive - amount of sensetive items
     * @param flammable - amount of flammable items
     */
    public Cargo(int priority,int shipID,  int ordinary, int explosive, int poisonous, int sensetive, int flammable) {
        this.priority = priority;
        this.sensetive = sensetive;
        this.ordinary = ordinary;
        this.poisonous = poisonous;
        this.explosive = explosive;
        this.flammable = flammable;
        this.shipID=shipID;
    }

    /**
     * Gets priority of Cargo
     * @return
     */
    public int getPriority() {
        return priority;
    }

    Cargo(int priority) {
        this.priority = priority;
    }

    /**
     * This method helps to implement Comparable interface
     * With the help of it cargo objects may contuct priorityQueue
     * @param anotherCargo
     * @return result of compare operation
     */
    @Override
    public int compareTo(Cargo anotherCargo) {
        if (this.priority > anotherCargo.getPriority()) return -1;
        if (this.priority < anotherCargo.getPriority()) return 1;
        return 0;
    }

    /**
     * This method converts Cargo object into string
     * @return converted object as a string
     */
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

    /**Sets Ships ID*/
    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

}
