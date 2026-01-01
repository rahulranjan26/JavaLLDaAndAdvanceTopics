public class CustomThreadName {
    public static void main(String[] args) {
        System.out.println("Just naming the custom threads");
        Thread boss = new Thread(() -> {
            System.out.println("Thread name is:" + Thread.currentThread().getName());
        }, "Boss Thread");

        Thread intern = new Thread(() -> {
            System.out.println("Thread name is:" + Thread.currentThread().getName());
        }, "Intern Thread");
        boss.start();
        intern.start();
    }
}
