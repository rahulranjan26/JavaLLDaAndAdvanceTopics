import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

public class MyClassForThreads implements Runnable {

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " started at " + LocalDateTime.now().toLocalTime());
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " ended at " + LocalDateTime.now().toLocalTime());
    }
}
