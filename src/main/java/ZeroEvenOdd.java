import java.util.stream.IntStream;

class ZeroEvenOdd {
    private int n;
    private int order = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero()  {
        //n sayı qədər zero
        IntStream.range(1, n+1).forEach(i ->
        {
            if(order == 0) {
                System.out.print("0");
                order = 1;
                notify();
            }else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public synchronized void even() throws InterruptedException {
        // 1-dən n-a qədər cüt əsəsdlər
        IntStream.range(1, n+1).forEach(i ->
                {

                    if(i % 2 == 0){
                        if(order == 1){
                            System.out.print(i);
                            order = 2;
                            notify();
                        }else{
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

    }

    public synchronized void odd() throws InterruptedException {
        // 1-dən n-a qədər tək əsəsdlər
        IntStream.range(1, n+1).forEach(i ->
        {
            if(i % 2 == 1){
                if(order == 2){
                    System.out.print(i);
                    order = 0;
                    this.notify();
                }else{
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ;
            }
            ;
        });
    }

    public static void main(String[] args){
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        new Thread(() -> zeroEvenOdd.zero()).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
