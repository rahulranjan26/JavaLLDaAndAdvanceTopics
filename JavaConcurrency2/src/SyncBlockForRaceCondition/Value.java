package SyncBlockForRaceCondition;

/**
 * A synchronized block in Java is a mechanism to ensure thread safety by
 * allowing only one thread at a time to execute a specific block of code.
 * It uses a monitor lock (intrinsic lock) on an object to prevent concurrent access,
 * reducing race conditions in multi-threaded programs.
 *
 */

public class Value {
    private int value = 0;

    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public void decrement() {
        synchronized (this) {
            this.value--;
        }
    }

    public int getValue() {
        return this.value;
    }

}
