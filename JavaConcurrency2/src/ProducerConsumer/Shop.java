package ProducerConsumer;

import java.util.ArrayList;

public class Shop {
    public final int size;
    public ArrayList<Object> chairs;

    public Shop(int size) {
        this.size = size;
        this.chairs = new ArrayList<>();
    }

    public int getSize() {
        return this.chairs.size();
    }

    public void insert() {
        System.out.println(Thread.currentThread().getName() + " Size after producing is:" + this.getSize());
        chairs.add(new Object());
    }

    public void remove() {
        System.out.println(Thread.currentThread().getName() + " Size is after consuming :" + this.getSize());
        chairs.remove(this.getSize() - 1);
    }


}
