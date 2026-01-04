package LeetcodeSemaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class LeetCode_1115 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("LeetCode problems 1115");
        Semaphore foo = new Semaphore(1);
        Semaphore bar = new Semaphore(0);


        Runnable fooCall = () -> {
            try {
                foo.acquire();
                System.out.print("Foo ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                bar.release();
            }


        };

        Runnable barCall = () -> {
            try {
                bar.acquire();
                System.out.print("Bar ");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                foo.release();
            }
        };


        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 25; i++) {  // run 5 cycles
            ex.submit(fooCall);
            ex.submit(barCall);

        }

        ex.shutdown();
        ex.awaitTermination(30, TimeUnit.SECONDS);


    }
}
