package SyncBlockForRaceCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("We will not handle race condition using sync block.");

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Value val = new Value();
        PrintNumbers pt = new PrintNumbers();
        Runnable added = () -> {
            for (int i = 0; i < 1000; i++) {
                val.increment();
            }
        };

        Runnable sub = () -> {
            for (int i = 0; i < 1000; i++) {
                val.decrement();
            }
        };

//        for (int i = 0; i < 50; i++) {
//            executorService.submit(()->val.increment());
//            executorService.submit(()->val.decrement());
//        }
        for (int i = 0; i < 500000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    pt.printNumbers(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }


        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println(val.getValue());
    }
}
