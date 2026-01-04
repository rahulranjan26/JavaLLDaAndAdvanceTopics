package ConcurrenctHashMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Regular HashMap (NOT thread-safe) ===");
        Map<String, Integer> unsafeMap = new HashMap<>();
        runWithGlobalLock(unsafeMap);  // Likely crashes or gives wrong count

        System.out.println("\n=== ConcurrentHashMap (thread-safe) ===");
        Map<String, AtomicInteger> safeMap = new ConcurrentHashMap<>();
        runConcurrentUpdates(safeMap);     // Works perfectly
    }

    private static void runConcurrentUpdates(Map<String, AtomicInteger> map) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 10 threads, each adding 1000 entries for the key "count"
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    AtomicInteger counter = map.get("count");
                    if (counter == null) {
                        counter = new AtomicInteger(0);
                        AtomicInteger existing = map.putIfAbsent("count", counter);
                        if (existing != null) {
                            counter = existing;  // Someone else created it first
                        }
                    }
                    counter.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Final count: " + map.get("count"));
        // Expected: 10 threads * 1000 = 10,000
        // With HashMap: often much lower or crashes (ConcurrentModificationException)
        // With ConcurrentHashMap: always ~10,000
    }

    private static void runWithGlobalLock(Map<String, Integer> map) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    Integer current = map.get("count");
                    int newValue = (current == null) ? 1 : current + 1;
                    map.put("count", newValue);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Final count: " + map.getOrDefault("count", 0));  // 10000
    }
}