package LeetcodeSemaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class LeetCode_1117 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("LeetCode 1117 - Building H2O (Corrected)");

        int numMolecules = 10;  // 10 H2O → 20 H + 10 O

        Semaphore hSem = new Semaphore(2);  // Max 2 H pending
        Semaphore oSem = new Semaphore(0);  // O waits for 2 H signals

        Runnable hydrogen = () -> {
            try {
                hSem.acquire();
                System.out.print("H");
                oSem.release();  // Each H signals O (2 signals needed)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable oxygen = () -> {
            try {
                oSem.acquire(2);  // Wait for EXACT 2 H signals
                System.out.print("O");
                hSem.release(2);  // Reset for next 2 H
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        ExecutorService ex = Executors.newFixedThreadPool(30);

        // Submit correct ratio
        for (int i = 0; i < numMolecules * 2; i++) {
            ex.submit(hydrogen);
        }
        for (int i = 0; i < numMolecules; i++) {
            ex.submit(oxygen);
        }

        ex.shutdown();
        ex.awaitTermination(20, TimeUnit.SECONDS);

        System.out.println("\nDone!");
    }
}