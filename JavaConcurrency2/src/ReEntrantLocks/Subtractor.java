package ReEntrantLocks;

public class Subtractor implements Runnable {
    private Value value;

    Subtractor(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        this.value.decrement();
    }
}
