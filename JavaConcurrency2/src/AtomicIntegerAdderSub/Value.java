package AtomicIntegerAdderSub;

import java.util.concurrent.atomic.AtomicInteger;

public class Value {
    private final AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        this.value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }

    public void decrement() {
        this.value.decrementAndGet();
    }
}
