package SemaphoresForProducerConsumer;

public class Consumer implements Runnable {
    private final Shop shop;

    public Consumer(Shop shop) {
        this.shop = shop;
    }


    @Override
    public void run() {
        while (true) {
            try {
                shop.removeItem();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
