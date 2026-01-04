package DiningPhilosopher;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Diners implements Runnable {
    private int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private final Semaphore room;



    public Diners(int id, Fork leftFork, Fork rightFork, Semaphore room) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.room = room;

    }

    public void dining() throws InterruptedException {
        room.acquire();
        synchronized (leftFork) {
            System.out.println("Diner " + id + " acquired the leftFork");
            synchronized (rightFork) {
                System.out.println("Diner " + id + " acquired the rightFork");
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println("Diner " + id + "  is done eating. Now diner is thinking");
            }
        }
        room.release();
    }

    public void thinking() throws InterruptedException {
        System.out.println("Diner " + id + "  is thinking");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        while (true) {
            try {
                dining();
                thinking();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
