package com.bakeryblueprint.modernjava.week04_doyeon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Q3 {

    public static void main(final String[] args) {
        //Q3.synchronousCallThreadName();
        Q3.asynchronousCallThreadName();
    }

    public static void synchronousCallThreadName() {
        final Q3 q3 = new Q3();

        final long start = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread Name : " + q3.returnThreadName());
        }
        final long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
    }

    public static void asynchronousCallThreadName() {
        final Q3 q3 = new Q3();
        final long start = System.nanoTime();
        final ExecutorService service = Executors.newFixedThreadPool(5);

        final List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final Future<String> submit = service.submit(() -> {
                return q3.returnThreadName();
            });
            futureList.add(submit);
        }

        futureList.forEach(it -> {
            try {
                System.out.println("threadName : " + it.get());
            } catch (final InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        service.shutdown();

        final long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");


    }

    public String returnThreadName() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final Exception e) {
        }
        return Thread.currentThread().getName();
    }
}