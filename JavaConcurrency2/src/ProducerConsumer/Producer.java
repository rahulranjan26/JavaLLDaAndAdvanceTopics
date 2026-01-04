package ProducerConsumer;

public class Producer implements Runnable {
    private final Shop shop;

    public Producer(Shop shop) {
        this.shop = shop;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (shop) {
                if (shop.getSize() < shop.size)
                    shop.insert();
            }
        }
    }
}
