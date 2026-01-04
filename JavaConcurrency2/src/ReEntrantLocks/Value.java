package ReEntrantLocks;

import java.util.concurrent.locks.ReentrantLock;

public class Value {
    private int value;
    private final ReentrantLock lock;

    Value(int value, ReentrantLock lock) {
        this.lock = lock;
        this.value = value;
    }

    public void increment() {
        lock.lock();
        this.value++;
        lock.unlock();
    }

    public void decrement() {
        lock.lock();
        this.value--;
        lock.unlock();
    }

    public int getValue() {
        return this.value;
    }


}
