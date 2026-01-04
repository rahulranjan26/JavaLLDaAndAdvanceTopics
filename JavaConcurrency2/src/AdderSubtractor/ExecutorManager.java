package AdderSubtractor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorManager {

    /**
     * <h2>Day 1: Threads & Basic Execution</h2>
     *
     * <p><strong>Thread</strong>: An independent path of execution within a process.
     * Threads share the same heap memory but have their own stack.</p>
     *
     * <p><strong>Key differences:</strong>
     * <ul>
     *   <li><strong>start()</strong>: Schedules a new thread and calls run() in it.</li>
     *   <li><strong>run()</strong>: Contains the code to execute. Calling run() directly executes in the current thread (no new thread).</li>
     *   <li><strong>join()</strong>: Current thread waits for this thread to finish. Use for coordination.</li>
     *   <li><strong>join(long millis)</strong>: Wait with timeout — returns normally on timeout (no exception).</li>
     *   <li><strong>sleep()</strong>: Pauses current thread. Throws InterruptedException (handle or re-interrupt).</li>
     *   <li><strong>interrupt()</strong>: Politely signals a thread to stop (sets flag). Check with isInterrupted().</li>
     *   <li><strong>daemon thread</strong>: Background thread. JVM exits when only daemon threads remain.</li>
     * </ul></p>
     *
     * <p><strong>Important</strong>: The JVM stays alive as long as there are non-daemon user threads running,
     * even if main() finishes.</p>
     *
     *
     *
     * <h2>Day 2: Race Conditions & Synchronization</h2>
     *
     * <p><strong>Race Condition</strong>: Bug that occurs when multiple threads access shared mutable state
     * concurrently, and at least one modifies it — final result depends on timing/order.</p>
     *
     * <p><strong>Example</strong>: counter++ is NOT atomic:
     * <ol>
     *   <li>Read current value</li>
     *   <li>Increment</li>
     *   <li>Write back</li>
     * </ol>
     * Two threads can read the same value → one increment is lost.</p>
     *
     * <p><strong>Critical Section</strong>: Code block that accesses shared resource — must be protected.</p>
     *
     * <p><strong>synchronized</strong>: Ensures only one thread enters the critical section at a time.
     * <ul>
     *   <li>synchronized method: Locks on 'this' (instance) or class (static).</li>
     *   <li>synchronized block: More flexible — lock on any object.</li>
     *   <li>Reentrant: Same thread can re-enter (lock count increases).</li>
     * </ul></p>
     *
     * <p><strong>volatile</strong>: Guarantees visibility across threads + prevents reordering.
     * Does <strong>NOT</strong> provide atomicity (volatile int x; x++ still races).</p>
     *
     * <p><strong>Happens-before</strong>: JMM guarantee — e.g., unlock happens-before next lock sees changes.</p>
     *
     *
     *
     * <h2>Day 3: Advanced Synchronization Tools</h2>
     *
     * <p><strong>wait()/notify()/notifyAll()</strong>: Used for thread communication inside synchronized block.
     * Proper pattern:
     * <pre>
     * synchronized(lock) {
     *     while (!condition) lock.wait();  // Release lock and wait
     *     // do work
     *     lock.notify();  // or notifyAll()
     * }
     * </pre></p>
     *
     * <p><strong>ReentrantLock</strong>: More flexible than synchronized.
     * Features: tryLock(), lockInterruptibly(), fairness, Condition.</p>
     *
     * <p><strong>ReadWriteLock</strong>: Multiple readers OR one writer — improves throughput for read-heavy scenarios.</p>
     *
     * <p><strong>Producer-Consumer</strong>:
     * <ul>
     *   <li>Classic: wait()/notify() on shared queue</li>
     *   <li>Modern: BlockingQueue (ArrayBlockingQueue, LinkedBlockingQueue) — preferred in production</li>
     * </ul></p>
     *
     *
     *
     * <h2>Day 4: Concurrency Utilities (java.util.concurrent)</h2>
     *
     * <p><strong>ExecutorService</strong>: Manages thread pool — never create raw threads in loops.</p>
     *
     * <p><strong>Runnable vs Callable</strong>:
     * <ul>
     *   <li>Runnable: void run() — fire and forget</li>
     *   <li>Callable: V call() — returns value, throws exception</li>
     *   <li>Use with Future: future.get() blocks for result</li>
     * </ul></p>
     *
     * <p><strong>ForkJoinPool</strong>: For divide-and-conquer (e.g., parallel MergeSort).
     * Uses RecursiveAction (no return) or RecursiveTask (with return).</p>
     *
     * <p><strong>Key Utilities</strong>:
     * <ul>
     *   <li>Semaphore: Limits concurrent access (e.g., connection pool)</li>
     *   <li>CountDownLatch: Wait for N events to complete</li>
     *   <li>CyclicBarrier: All threads wait for each other (reusable)</li>
     *   <li>Concurrent collections: ConcurrentHashMap, CopyOnWriteArrayList</li>
     *   <li>Atomic variables: AtomicInteger — lock-free thread-safe operations</li>
     * </ul></p>
     *
     * <p><strong>Deadlock Prevention</strong>: Consistent lock ordering, tryLock(), avoid nested locks.</p>
     */

    public static void main(String[] args) {
        System.out.println("We know the usual thread creation in java is so tiresome." +
                "So we use Thread pool manager, ExecutorService, to manage the pools of group of threads");

        Value v = new Value(0);
        Runnable adder = () -> {
            for (int i = 0; i < 1000; i++) {
                v.setValue(v.getValue() + 1);
            }
        };

        Runnable sub = () -> {
            for (int i = 0; i < 1000; i++) {
                v.setValue(v.getValue() - 1);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(adder);
            executorService.submit(sub);
        }
        executorService.shutdown();

        System.out.println(v.getValue());

        /*
         * Your code had only **2 tasks** (1 adder + 1 subtractor), so concurrency was too low — not enough interleaving to reliably trigger visible lost updates in `get + modify + set`.
         * Your operations were mathematically symmetric (or too few), so even with some races, adds/subtracts canceled out perfectly → always ~0.
         * My code used **200 tasks** with simple +1/-1 operations → massive concurrent access → race condition exploded with random final values.
         */
    }


}
