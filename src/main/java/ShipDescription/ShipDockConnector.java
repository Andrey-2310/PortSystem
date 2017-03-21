package ShipDescription;

import java.util.Scanner;

/**
 * Created by Андрей on 21.03.2017.
 */
public class ShipDockConnector {
    private int timeToStay;
    private int cargoPriority;
    private boolean available;
ShipDockConnector(){
    this.available = false;
}
    public synchronized int GetTimeToStay() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notifyAll();
        return timeToStay;
    }

    public synchronized int GetCargoPriority() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notifyAll();
        return cargoPriority;
    }

    public synchronized void SetTimeToStay() {
        try {
            Thread.sleep(2000);
            while (available == true) {
                wait();
            }
        } catch (InterruptedException e) {
        }
        Scanner in=new Scanner(System.in);
        System.out.println("Введите время для остановки");
        timeToStay =in.nextInt();
        System.out.println("Введите приоритет груза");

        available = true;
        notifyAll();
    }
    public synchronized void SetCargoPriority() {
        try {
            Thread.sleep(2000);
            while (available == true) {
                wait();
            }
        } catch (InterruptedException e) {
        }
        Scanner in=new Scanner(System.in);
        System.out.println("Введите приоритет груза");
        cargoPriority =in.nextInt();
        available = true;
        notifyAll();
    }
}
