package CargoDescription;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Cargo implements Comparable<Cargo> {

    private int priority;

    private int sensetive;
    private int ordinary;
    private int poisonous;
    private int explosive;
    private int flammable;

    private String destination;
    private String instruction;
    private String current;

    public int getSensetive() {return sensetive;}

    public int getOrdinary() {return ordinary;}

    public int getPoisonous() {return poisonous;}

    public int getExplosive() {return explosive;}

    public int getFlammable() {return flammable;}

    public String getDestination() {return destination;}

    public String getInstruction() {return instruction;}

    public String getCurrent() {return current;}

    public Cargo(String current, String destination, int priority, int ordinary, int explosive,
                 int poisonous, int sensetive, int flammable, String instruction) {
        this.priority = priority;
        this.sensetive = sensetive;
        this.ordinary = ordinary;
        this.poisonous = poisonous;
        this.explosive = explosive;
        this.flammable = flammable;
        this.destination = destination;
        this.instruction = instruction;
        this.current=current;

    }

    public int getPriority() {
        return priority;
    }

    Cargo(int priority){
        this.priority=priority;
    }
    @Override
    public int compareTo(Cargo anotherCargo) {
        if(this.priority>anotherCargo.getPriority()) return -1;
        if(this.priority<anotherCargo.getPriority()) return 1;
        return 0;
    }
}
