package LeetcodeSemaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class LeetCode_1114 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("LeetCode problems");
        Semaphore first = new Semaphore(2);
        Semaphore second = new Semaphore(0);
        Semaphore third = new Semaphore(0);

        Runnable firstCall = () -> {
            try {
                first.acquire();
                System.out.println("First");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                second.release();
            }


        };

        Runnable secondCall = () -> {
            try {
                second.acquire();
                System.out.println("Second");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                third.release();
            }
        };

        Runnable thirdCall = () -> {
            try {
                third.acquire();
                System.out.println("Third");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                first.release();
            }
        };


        ExecutorService ex = Executors.newFixedThreadPool(10);
        while (true) {  // run 5 cycles
            ex.submit(firstCall);
            ex.submit(secondCall);
            ex.submit(thirdCall);
        }

//        ex.shutdown();
//        ex.awaitTermination(30, TimeUnit.SECONDS);


    }
}
