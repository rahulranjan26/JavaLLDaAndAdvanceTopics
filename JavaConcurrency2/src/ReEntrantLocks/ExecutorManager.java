package ReEntrantLocks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorManager {

    /**
     * Why ReentrantLock?
     *
     * <h4>More features than synchronized: tryLock(), fairness, multiple Conditions, interruptible lock.</h4>
     * <h4>Explicit lock/unlock → clearer control (but you must remember unlock() in finally!)</h4>
     * <h4>Reentrant: same thread can acquire the lock multiple times (lock count increases)</h4>
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("We will now fix the race condition using the Reentrant locking.");


        ReentrantLock lock = new ReentrantLock();
        Value v = new Value(0, lock);
        Runnable adder = () -> {
            for (int i = 0; i < 1000; i++) {
                v.increment();
            }
        };

        Runnable sub = () -> {
            for (int i = 0; i < 1000; i++) {
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
        System.out.println(v.getValue());


    }


}
