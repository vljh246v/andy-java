package com.bakeryblueprint.modernjava.week04;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class InsideParallelStream {

  public static void main(final String[] args) {
    final List<Long> collect = LongStream.rangeClosed(0, 30).boxed().collect(Collectors.toList());

    collect.parallelStream()
        .forEach(it -> {
          // 현재 스레드 정보
          System.out
              .println(LocalDateTime.now() + ", Thread Name : " + Thread.currentThread().getName()
                  + ", value : " + it);

          try {
            TimeUnit.SECONDS.sleep(1);
          } catch (final InterruptedException e) {
            e.printStackTrace();
          }
        });
  }

}
