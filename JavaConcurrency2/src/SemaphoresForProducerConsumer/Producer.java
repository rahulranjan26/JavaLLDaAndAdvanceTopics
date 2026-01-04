package SemaphoresForProducerConsumer;

public class Producer implements Runnable {
    private final Shop shop;

    public Producer(Shop shop) {
        this.shop = shop;
    }


    @Override
    public void run() {
        while (true) {
            try {
                shop.insert();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
