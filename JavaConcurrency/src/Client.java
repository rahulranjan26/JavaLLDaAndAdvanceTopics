import java.time.LocalDateTime;
import java.time.LocalTime;

public class Client {
    public static void main(String args[]) {
        System.out.println("Hello from Concurrency chapter at " + LocalDateTime.now().toLocalTime());
        MyClassForThreads x = new MyClassForThreads();
        Thread t1 = new Thread(x);
        t1.start();
//        t1.interrupt();
        try {
            t1.join(6000);
            System.out.println("Main thread waited for : " + LocalDateTime.now().toLocalTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main thread is ended at : " + LocalDateTime.now().toLocalTime());

    }
}
