package az.code.threads;

import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        Counter c=new Counter();
//        Thread t1=new Thread(()-> IntStream.range(0,1000).forEach((i)->c.increase()));
//        Thread t2=new Thread(()-> IntStream.range(0,1000).forEach((i)->c.increase()));
//
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(c.count);

        int threadCount = 10000;
        spawnThreads(threadCount);
    }

    public static void spawnThreads(int n) throws InterruptedException {
        Thread t = new Thread(() -> {
           if(n == 0){
               System.out.println(Thread.currentThread().getName());
               return;
           }
            try {
                spawnThreads(n-1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.println(Thread.currentThread().getName());
    }
}

class Counter{
    int count;
    public synchronized void increase(){
        count++;
    }
}
