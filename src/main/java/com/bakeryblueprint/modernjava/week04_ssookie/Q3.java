package com.bakeryblueprint.modernjava.week04_ssookie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Q3 {

    public static void main(final String[] args) {
        com.bakeryblueprint.modernjava.week04_ssookie.Q3.synchronousCallThreadName();
        com.bakeryblueprint.modernjava.week04_ssookie.Q3.asynchronousCallThreadName();
    }

    public static void synchronousCallThreadName() {
        final com.bakeryblueprint.modernjava.week04.homework.Q3 q3 = new com.bakeryblueprint.modernjava.week04.homework.Q3();

        final long start = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread Name : " + q3.returnThreadName());
        }
        final long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
    }

    public static void asynchronousCallThreadName() {
        final com.bakeryblueprint.modernjava.week04.homework.Q3 q3 = new com.bakeryblueprint.modernjava.week04.homework.Q3();
        final long start = System.nanoTime();

        // 비즈니스 로직을 호출하는 부분을 비동기로 변경
        final ExecutorService service = Executors.newFixedThreadPool(5);
        final List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            // 비동기식으로 returnThreadName 을 호출하는 로직을 작성 하시오
            final Future<String> submit = service.submit(() -> {
                return new Q3().returnThreadName();
            });
            futureList.add(submit);
        }
        futureList.forEach(it -> {
            try {
                System.out.printf("비동기 Thread Name : %s\n", it.get());
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

