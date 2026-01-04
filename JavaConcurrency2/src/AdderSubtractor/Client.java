package AdderSubtractor;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        /*
         "We will work with the following concurrency topics:" +
         "1. Race condition" +
         "2. Critical Section" +
         "3. Synchronized Keyword" +
         "4. Volatile Keyword" +
         "5. JMM --> Java Memory Model"
         */

        System.out.println("Welcome to Concurrency chapter 2");
        Value value = new Value(0);
        Adder adder = new Adder(value);
        Subtractor subtractor = new Subtractor(value);
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(adder);
            thread.start();
//            thread.join();
        }
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(subtractor);
            thread.start();
//            thread.join();
        }

        System.out.println("The output is :" + value.getValue());


    }
}
