package AtomicIntegerAdderSub;

public class Subtractor implements Runnable {
    private final Value value;

    Subtractor(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        this.value.decrement();
    }
}
