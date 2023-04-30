package lab4;

import lab4.entity.Building;
import lab4.model.Generator;
import lab4.model.Request;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter the number of floors in the building");
        int floors = new Scanner(System.in).nextInt();
        BlockingQueue<Request> requests = new LinkedBlockingQueue<>(20);
        Building building = new Building("1", "2", floors, requests);

        building.startElevators();

        System.out.println("ENTER ANYTHING TO TERMINATE THE PROGRAM");

        Generator generator = new Generator(requests, 0, floors, 5000);
        Thread generatorThread = new Thread(generator);
        generatorThread.start();

        new Scanner(System.in).next();

        System.out.println("SHUTTING DOWN");
        generator.stop();
        building.stopElevators();
        try {
            building.getManagerThread().join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
