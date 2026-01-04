package AtomicIntegerAdderSub;

public class Adder implements Runnable {
    private final Value value;

    public Adder(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        this.value.increment();
    }
}
