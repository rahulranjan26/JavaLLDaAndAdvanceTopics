package ProducerConsumer;

public class Consumer implements Runnable {
    private final Shop shop;

    public Consumer(Shop shop) {
        this.shop = shop;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (shop) {
                if (shop.getSize()>0)
                    shop.remove();
            }
        }
    }
}
