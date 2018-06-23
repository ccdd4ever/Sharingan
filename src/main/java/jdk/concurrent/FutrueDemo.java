package jdk.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutrueDemo {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future<Integer> square(final Integer i) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return i * i;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> square = new FutrueDemo().square(10);
        while (!square.isDone()) {
            Thread.sleep(300);
            System.out.println("calculating");
        }
        System.out.println("result:" + square.get());

    }

}
