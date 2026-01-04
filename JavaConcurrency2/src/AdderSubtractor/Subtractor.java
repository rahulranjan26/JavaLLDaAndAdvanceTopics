package AdderSubtractor;

public class Subtractor implements Runnable {
    private Value value;

    Subtractor(Value value) {
        this.value = value;
    }

    @Override
    public void run() {
        this.value.setValue(this.value.getValue() - 1);
    }
}
