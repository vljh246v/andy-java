package com.bakeryblueprint.modernjava.week04;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class InsideParallelStream2 {

  public static void main(final String[] args) {
    final List<Long> collect =
        LongStream
            .rangeClosed(0, 10)
            .boxed()
            .collect(Collectors.toList());

    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");

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
  }

}
