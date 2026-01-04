package AtomicIntegerAdderSub;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorManager {

    /*
    We will try and fix the race condition using the Atomic integers.
    Atomic integers are lock free and uses CAS algo ie Compare and Swap Algo.
    High Performance
    No Explicit locking
    Clean Code
     */


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Fixing the Race using Atomic Integer");

        Value v = new Value();
        Runnable adder = () -> {
            for (int i = 0; i < 10000; i++) {
                v.increment();
            }
        };

        Runnable sub = () -> {
            for (int i = 0; i < 10000; i++) {
                v.decrement();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(adder);
            executorService.submit(sub);
        }
        executorService.shutdown();

        executorService.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Final value after wait: " + v.getValue());  // ALWAYS 0

        /*
         * Your code had only **2 tasks** (1 adder + 1 subtractor), so concurrency was too low — not enough interleaving to reliably trigger visible lost updates in `get + modify + set`.
         * Your operations were mathematically symmetric (or too few), so even with some races, adds/subtracts canceled out perfectly → always ~0.
         * My code used **200 tasks** with simple +1/-1 operations → massive concurrent access → race condition exploded with random final values.
         */
    }


}
