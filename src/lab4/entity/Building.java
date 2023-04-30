package lab4.entity;

import lab4.model.Direction;
import lab4.model.Request;

import java.util.concurrent.BlockingQueue;

public class Building {
    private Manager manager;

    private Thread managerThread;


    public Building(){}

    public Building(String elevator1Name, String elevator2Name, int floors, BlockingQueue<Request> requests) {
        Elevator elevator1 = new Elevator(elevator1Name, Direction.STOPPED, 0, -1, requests, floors);
        Elevator elevator2 = new Elevator(elevator2Name, Direction.STOPPED, 0, -1, requests, floors);
        this.manager = new Manager(elevator1, elevator2, requests);
    }

    public void startElevators() {
        managerThread = new Thread(manager);
        managerThread.start();
    }

    public void stopElevators() {
        manager.stop();
    }

    public Thread getManagerThread() {
        return managerThread;
    }
}
