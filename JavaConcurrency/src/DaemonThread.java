import java.time.LocalDateTime;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from Daemon Thread");
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread is working");
                try {
                    Thread.sleep(2000);
//                    System.out.println(LocalDateTime.now().toLocalTime());
                } catch (InterruptedException e) {
                    System.out.println("Daemon interrupted");
                    break;
                }
            }
        });

        LocalDateTime endTime = LocalDateTime.now().plusSeconds(10);

        Thread t2 = new Thread(() -> {
//            System.out.println("Non daemon thread");
            while (LocalDateTime.now().isBefore(endTime)) {
                System.out.println("Non daemon thread is working");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(LocalDateTime.now().toLocalTime());
            }

        });

        t1.setDaemon(true);
        t1.start();
        t2.start();
        t1.join(10000);
        System.out.println("Main thread has ended");

    }
}
/*
 * ✅ Daemon threads depend on JVM lifetime
 * ✅ JVM lifetime depends on non-daemon threads
 * ✅ Main thread is just another non-daemon thread (special but not special to daemons)
 */