package ShipDescription;

import Scenes.PortWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Scanner;

/**
 * Created by Андрей on 21.03.2017.
 */
public class ShipDockConnector {
    private int timeToStay;
    private int cargoPriority;
    private boolean available;
    private PortWindow portWindow;

    public ShipDockConnector(PortWindow portWindow) {

        this.available = false;
        this.portWindow = portWindow;
    }

    public synchronized void SetTimeToStay(int numberOfDock) {
        try {
            while (available == true) {
                wait();
            }
        } catch (InterruptedException e) {
        }
        Platform.runLater(() -> {
            portWindow.getDockLabels()[numberOfDock - 1].setText("Введите время для остановки");
            portWindow.getDockOkButtons()[numberOfDock - 1].setOnMousePressed(event -> {
                timeToStay = Integer.parseInt(portWindow.getDockTextFields()[numberOfDock - 1].getText());
                available = true;
            });
        });
        //   });
        // timeToStay=portWindow.GetTimeFromUI(numberOfDock);
        // available = true;

        //   Scanner in = new Scanner(System.in);
        // System.out.println("Введите время для остановки");
      /*  Platform.runLater(() -> {
            label.setText("Введите время для остановки");
            button.setOnMousePressed(event -> {
                timeToStay = Integer.parseInt(textField.getText());

            });

        });*/
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {

            if (available) {
                System.out.println("Здарова");
                notifyAll();
                return;
            }
        }
        //  timeToStay = in.nextInt();

    }

    public synchronized void SetCargoPriority(int numberOfDock) {
        try {
            while (available == true) {
                wait();
            }
        } catch (InterruptedException e) {
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Введите приоритет груза");
        cargoPriority = in.nextInt();
        available = true;
        notifyAll();
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
        System.out.println(timeToStay);
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


}
