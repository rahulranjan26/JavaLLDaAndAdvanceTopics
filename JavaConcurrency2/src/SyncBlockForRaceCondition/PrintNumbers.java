package SyncBlockForRaceCondition;

import java.util.concurrent.locks.ReentrantLock;

public class PrintNumbers {
    private final ReentrantLock lock = new ReentrantLock();

    public void printNumbers(int x) throws InterruptedException {
        lock.lock();
        System.err.println(Thread.currentThread().getName() + " We are printing number: " + x);
        lock.unlock();
        //        Thread.sleep(500);

    }

}
