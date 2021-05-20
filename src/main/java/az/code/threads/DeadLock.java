package az.code.threads;

public class DeadLock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> meth1()).start();
        new Thread(() -> meth2()).start();
    }
    public static void meth1(){
        synchronized (lock1){
            System.out.println("inside meth1 - lock1");
            synchronized (lock2){
                System.out.println("inside meth1 - lock2");
            }
        }
    }

    public static void meth2(){
        synchronized (lock1){
            System.out.println("inside meth2 - lock2");
            synchronized (lock2){
                System.out.println("inside meth2 - lock1");
            }
        }
    }
}
