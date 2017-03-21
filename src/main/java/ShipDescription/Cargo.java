package ShipDescription;

/**
 * Created by Андрей on 16.03.2017.
 */
public class Cargo implements Comparable<Cargo> {

    private int priority;

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
