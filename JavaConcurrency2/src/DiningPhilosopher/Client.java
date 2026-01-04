package DiningPhilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        int NUM_OF_PHILOSOPHERS = 5;
        Semaphore room = new Semaphore(4);
        Fork[] forks = new Fork[NUM_OF_PHILOSOPHERS];
        Diners[] philosophers = new Diners[NUM_OF_PHILOSOPHERS];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < 5; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % NUM_OF_PHILOSOPHERS];
            philosophers[i] = new Diners(i, leftFork, rightFork, room);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(philosophers[i]);
        }

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

    }
}
