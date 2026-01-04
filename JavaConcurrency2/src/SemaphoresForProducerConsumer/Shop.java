package SemaphoresForProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Shop {
    public final int size;
    public List<Object> chairs;
    private Semaphore producer;
    private Semaphore consumer;
    private Semaphore mutex = new Semaphore(1);

    public Shop(int size, Semaphore producer, Semaphore consumer) {
        this.size = size;
        this.chairs = new ArrayList<>();
        this.producer = producer;
        this.consumer = consumer;
    }

    public int getSize() {
        return this.chairs.size();
    }

    public void insert() throws InterruptedException {
        producer.acquire();
        mutex.acquire();
        this.chairs.add(new Object());
        System.out.println(Thread.currentThread().getName() + " Size after producing is:" + this.getSize());
        mutex.release();
        consumer.release();
    }

    public void removeItem() throws InterruptedException {
        consumer.acquire();
        mutex.acquire();
        this.chairs.removeLast();
        System.out.println(Thread.currentThread().getName() + " Size is after consuming :" + this.getSize());
        mutex.release();
        producer.release();
    }


}
