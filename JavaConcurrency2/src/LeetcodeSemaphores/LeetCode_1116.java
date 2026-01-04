package LeetcodeSemaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LeetCode_1116 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("LeetCode 1116 - Print Zero Even Odd");

        int n = 10;  // Print up to 10 → output: 01020304050607080910

        Semaphore zeroSem = new Semaphore(1);  // Zero starts
        Semaphore oddSem = new Semaphore(0);   // For odd numbers
        Semaphore evenSem = new Semaphore(0);  // For even numbers

        int[] current = {1};  // Shared counter starting from 1

        Runnable zeroTask = () -> {
            for (int i = 1; i <= n; i++) {
                try {
                    zeroSem.acquire();
                    System.out.print(0);
                    if (i % 2 == 1) {
                        oddSem.release();   // Next is odd
                    } else {
                        evenSem.release();  // Next is even
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable oddTask = () -> {
            for (int i = 1; i <= n; i += 2) {
                try {
                    oddSem.acquire();
                    System.out.print(i);
                    zeroSem.release();  // Allow next zero
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable evenTask = () -> {
            for (int i = 2; i <= n; i += 2) {
                try {
                    evenSem.acquire();
                    System.out.print(i);
                    zeroSem.release();  // Allow next zero
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        ExecutorService ex = Executors.newFixedThreadPool(3);

        ex.submit(zeroTask);
        ex.submit(oddTask);
        ex.submit(evenTask);

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\nDone!");
    }
}