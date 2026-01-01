import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;

public class TimeoutTheThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from TimeoutTheThread");
        System.out.println("Main thread start at: " + LocalDateTime.now().toLocalTime());

        Thread t1 = new Thread(() -> {
            System.out.println("Working from inside the thread: " + Thread.currentThread().getName());
            try {
                System.out.println("Thread is sleeping at: " + LocalDateTime.now().toLocalTime());
                Thread.sleep(10000);
                System.out.println("Thread is woke up at: " + LocalDateTime.now().toLocalTime());
            } catch (InterruptedException e) {
                System.out.println("Task cancelled due to timeout " + e.getLocalizedMessage());
                Thread.currentThread().interrupt();  // Restore flag
            }
        });
        t1.start();
        t1.join(3000);
        if (t1.isAlive()) {
            t1.interrupt();
            System.out.println("Thread is interrupted at: " + LocalDateTime.now().toLocalTime());
            t1.join();
        }
        System.out.println("Main thread is ended at: " + LocalDateTime.now().toLocalTime());
    }
}
