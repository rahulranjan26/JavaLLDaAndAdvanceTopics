import java.time.LocalDateTime;

public class FOOBARBAZ {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("FOOBARBAZ");
        System.out.println("Main thread starts at :" + LocalDateTime.now().toLocalTime());

        Thread foo = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("FOO");
            }
        });

        Thread bar = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("BAR");
            }
        });

        Thread baz = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("BAZ");
            }
        });

        foo.start();
        foo.join();
        bar.start();
        bar.join();
        baz.start();
        baz.join();


    }
}
