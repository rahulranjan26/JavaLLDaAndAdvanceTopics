package ReEntrantLocks;

public class Adder implements Runnable {
    private Value value;

    public Adder(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        this.value.increment();
    }
}
