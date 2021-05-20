package az.code.threads;

import java.util.stream.IntStream;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        CrowdControl crowdControl = new CrowdControl();

        Thread t1  = new Thread(() -> IntStream.range(1, 3).parallel().forEach(i -> {
            try {
                crowdControl.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        Thread t2  = new Thread(() -> IntStream.range(1, 10).parallel().forEach(i -> {
            try {
                crowdControl.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        t2.start();
        Thread.sleep(3000);
        t1.start();

        t1.join();
        t2.join();
        System.out.println(crowdControl.getCurrentCount());
    }
}
