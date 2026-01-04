package SemaphoresForProducerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Lets study about Semaphores and producer and consumers problems");

        Semaphore producerSemaphore = new Semaphore(25);
        Semaphore consumerSemaphore = new Semaphore(0);
        Shop shop = new Shop(25, producerSemaphore, consumerSemaphore);
        Producer producer = new Producer(shop);
        Consumer consumer = new Consumer(shop);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i <= 5; i++)
            executorService.submit(producer);

        for (int i = 0; i <= 20; i++)
            executorService.submit(consumer);

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
