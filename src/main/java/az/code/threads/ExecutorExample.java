package az.code.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        IntStream.range(1, 101).forEach(i -> executorService.submit(() -> {
//            System.out.println(i + " - " + Thread.currentThread().getName());
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }));
//        executorService.submit(() -> System.out.println("Hello"));
//        executorService.shutdown();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> result = new ArrayList<>();
        List<Future<String>> result2 = new ArrayList<>();

        new Thread(() ->{
            IntStream.range(1, 100).forEach(i -> {
             result.add(executorService.submit(() -> "kvadrat" + i*i + "- " + Thread.currentThread().getName()));
        });
            result.stream().forEach(i -> {
                try {
                    System.out.println(i.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
    }
        ).start();
        new Thread(() -> IntStream.range(1, 100).parallel().forEach(i -> {
            result2.add(executorService.submit(() -> "2 vur" + i*2 + "- " + Thread.currentThread().getName()));
        })).start();


    }
}
