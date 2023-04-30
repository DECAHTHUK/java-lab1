package lab4.entity;

import lab4.model.Direction;
import lab4.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Elevator implements Runnable{
    private String name;

    private Direction currentState;

    private int currentFloor;

    private int callingFloor;

    private int floors;

    private BlockingQueue<Request> requests;


    @Override
    public void run() {
        System.out.printf("Elevator #%s(at floor %d) got a request to floor %d with direction of %s.\n", name, currentFloor, callingFloor, currentState.toString());
        List<Integer> concurrentRequests = new ArrayList<>();
        int step = currentFloor < callingFloor ? 1 : -1;

        ride(step, concurrentRequests);
        System.out.printf("Elevator #%s reached destined calling floor at %d and took a passenger. Moving %s\n", name, callingFloor, currentState);

        callingFloor = currentState == Direction.DOWN ? 0 : floors;
        step = currentState == Direction.DOWN ? -1 : 1;
        concurrentRequests.clear();

        ride(step, concurrentRequests);

        System.out.printf("Elevator #%s ended its ride at floor %d\n", name, callingFloor);
        this.callingFloor = -1;
        this.currentState = Direction.STOPPED;
    }

    public void ride(int step, List<Integer> concurrentRequests) {
        try {
            while (currentFloor != callingFloor) {
                int sizeBefore = concurrentRequests.size();
                if (step == -1) {
                    concurrentRequests.addAll(requests.stream()
                            .filter(t -> t.getCallingFloor() <= currentFloor
                                    && (t.getDirection() == currentState || callingFloor == 0 && t.getDirection() == Direction.DOWN))
                            .map(Request::getCallingFloor).toList());
                } else {
                    concurrentRequests.addAll(requests.stream()
                            .filter(t -> t.getCallingFloor() >= currentFloor
                                    && (t.getDirection() == currentState || callingFloor == floors && t.getDirection() == Direction.UP))
                            .map(Request::getCallingFloor).toList());
                }
                for (int i = sizeBefore; i < concurrentRequests.size(); i++) {
                    requests.remove(new Request(concurrentRequests.get(i), currentState));
                }

                while (concurrentRequests.contains(currentFloor)) {
                    System.out.printf("Elevator #%s(at floor %d) took a passenger.\n", name, currentFloor);
                    concurrentRequests.remove(Integer.valueOf(currentFloor));
                }
                System.out.printf("Elevator #%s moving %s to floor %d\n", name, step == 1 ? "UP" : "DOWN", currentFloor + step);
                currentFloor += step;

                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public Elevator() {
    }

    public Elevator(String name, Direction currentState, int currentFloor, int callingFloor, BlockingQueue<Request> requests, int floors) {
        this.name = name;
        this.currentState = currentState;
        this.currentFloor = currentFloor;
        this.callingFloor = callingFloor;
        this.requests = requests;
        this.floors = floors;
        System.out.println("Elevator #" + name + " started it's schedule");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Direction currentState) {
        this.currentState = currentState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCallingFloor() {
        return callingFloor;
    }

    public void setCallingFloor(int callingFloor) {
        this.callingFloor = callingFloor;
    }
}
