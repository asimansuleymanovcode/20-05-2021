package az.code.threads;

public class CrowdControl {
    int currentCount = 0;
    public static Object lock = new Object();

    public synchronized  void increment() throws InterruptedException {

        currentCount++;
        System.out.println("notify - " + Thread.currentThread().getName());
        notify();

    }

    public  synchronized  void decrement() throws InterruptedException {
        if(currentCount <= 0){
            System.out.println("wait");
            wait();
            notify();
            notify();
            notify();
            notify();
            notify();
            notify();
            notify();
            notify();
        }
            currentCount--;
    }
    public  int getCurrentCount(){
        return this.currentCount;
    }

}
