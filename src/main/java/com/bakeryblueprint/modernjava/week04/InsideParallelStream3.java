package com.bakeryblueprint.modernjava.week04;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class InsideParallelStream3 {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    final List<Long> collect =
        LongStream
            .rangeClosed(0, 10)
            .boxed()
            .collect(Collectors.toList());

    final ForkJoinPool customForkJoinPool = new ForkJoinPool(2);

    customForkJoinPool.submit(() -> {
      System.out.println("Pool Size : " + customForkJoinPool.getPoolSize());

      collect.parallelStream()
          .forEach(it -> {
            // 현재 스레드 정보
            System.out
                .println(LocalDateTime.now() + ", Thread Name : " + Thread.currentThread().getName()
                    + ", value : " + it);

            try {
              TimeUnit.SECONDS.sleep(2);
            } catch (final InterruptedException e) {
              e.printStackTrace();
            }
          });
    }).get();
  }

}
