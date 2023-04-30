package lab4.model;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Generator implements Runnable{
    private final BlockingQueue<Request> requests;

    private final int minFloor;

    private final int maxFloor;

    private final long intervalInMs;

    private boolean stopped;

    public Generator(BlockingQueue<Request> requests, int minFloor, int maxFloor, long intervalInMs) {
        this.requests = requests;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.intervalInMs = intervalInMs;
        stopped = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        while (!stopped) {
            Request request = new Request(random.nextInt(minFloor, maxFloor), random.nextBoolean() ? Direction.UP : Direction.DOWN);
            requests.add(request);
            System.out.println("New request: " + request.getCallingFloor() + " " + request.getDirection());
            try {
                Thread.sleep(intervalInMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        stopped = true;
    }
}
