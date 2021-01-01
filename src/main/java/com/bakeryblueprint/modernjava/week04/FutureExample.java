package com.bakeryblueprint.modernjava.week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureExample {

  public static void main(final String[] args) {
    final FutureExample futureExample = new FutureExample();
    futureExample.executeTest();
  }

  public Callable<Long> calSquare(final long value) {
    final Callable<Long> callable = new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        final Long returnValue = value * value;
        TimeUnit.SECONDS.sleep(1);
        System.out.println(value + "의 제곱근은 " + returnValue);
        return returnValue;
      }
    };
    return callable;
  }

  public void executeTest() {
    final List<Long> sampleDataList =
        Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
    final List<Future<Long>> futureList = new ArrayList<>();

    final ExecutorService servicePool = Executors.newFixedThreadPool(4);

    for (final Long sampleValue : sampleDataList) {
      final Future<Long> future = servicePool.submit(this.calSquare(sampleValue));
      futureList.add(future);
    }

    Long sumValue = 0L;
    for (final Future<Long> future : futureList) {
      try {
        sumValue += future.get();
      } catch (final ExecutionException | InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("최종 합계 : " + sumValue);

    servicePool.shutdown();
  }

}
