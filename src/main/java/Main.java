import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Thread myThread = new Thread(() -> IntStream.range(1, 1000).forEach(i-> System.out.println(Thread.currentThread().getName() + " - " + i)));
        Thread myThread2 = new Thread(() -> {
            System.out.println("First line");
            while(!Thread.interrupted()){
                System.out.println("444");
            }
        });
//        myThread.start();
        myThread2.start();
//        Thread.sleep(100);
        myThread2.interrupt();
//        myThread.join();
        myThread2.join();
        System.out.println("After started thread");
    }
}
