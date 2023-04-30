package lab4.entity;

import lab4.model.Direction;
import lab4.model.Request;

import java.util.concurrent.BlockingQueue;

public class Manager implements Runnable{
    private Elevator elevator1;

    private Elevator elevator2;

    private BlockingQueue<Request> requests;

    private boolean stopped;


    @Override
    public void run() {
        this.stopped = false;
        Thread elevatorThread1 = new Thread(elevator1);
        Thread elevatorThread2 = new Thread(elevator2);

        try {
            while (!stopped) {
                if (requests.isEmpty()) {
                    Thread.sleep(200);
                } else if (elevator1.getCurrentState() == Direction.STOPPED || elevator2.getCurrentState() == Direction.STOPPED){
                    Thread.sleep(2000);
                    Request request = requests.poll();
                    if (request != null) {
                        if (elevator1.getCurrentState() == Direction.STOPPED && Math.abs(elevator1.getCurrentFloor() - request.getCallingFloor()) <=
                                Math.abs(elevator2.getCurrentFloor() - request.getCallingFloor()) || elevator2.getCurrentState() != Direction.STOPPED) {
                            elevator1.setCallingFloor(request.getCallingFloor());
                            elevator1.setCurrentState(request.getDirection());
                            elevatorThread1 = new Thread(elevator1);
                            elevatorThread1.start();
                        } else {
                            elevator2.setCallingFloor(request.getCallingFloor());
                            elevator2.setCurrentState(request.getDirection());
                            elevatorThread2 = new Thread(elevator2);
                            elevatorThread2.start();
                        }
                    }
                }
            }
            elevatorThread1.join();
            elevatorThread2.join();
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void stop() {
        stopped = true;
    }

    public Manager(Elevator elevator1, Elevator elevator2, BlockingQueue<Request> requests) {
        this.elevator1 = elevator1;
        this.elevator2 = elevator2;
        this.requests = requests;
        this.stopped = false;
    }
}
