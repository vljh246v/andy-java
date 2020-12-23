package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DelayedTaskExample {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    final ScheduledExecutorService exeService = Executors
        .newSingleThreadScheduledExecutor();

//    exeService.schedule(() -> System.out.println("TODO 1"), 5, TimeUnit.SECONDS);
//    exeService.schedule(() -> System.out.println("TODO 2"), 10, TimeUnit.SECONDS);
//    exeService.schedule(() -> System.out.println("TODO 3"), 15, TimeUnit.SECONDS);
//
//    exeService.shutdown();

    // schedule(Runnable command, long delay, TimeUnit unit)
    exeService
        .schedule(() -> System.out.println(
            "schedule(Runnable command, long delay, TimeUnit unit), Thread.currentThread() : "
                + Thread.currentThread()),
            5, TimeUnit.SECONDS);

    // schedule(Callable<V> callable, long delay, TimeUnit unit)
    final ScheduledFuture<Integer> schedule = exeService.schedule(() -> {
      System.out.println(
          "schedule(Callable<V> callable, long delay, TimeUnit unit), Thread.currentThread() : "
              + Thread.currentThread());
      return 1;
    }, 5, TimeUnit.SECONDS);
    System.out.println(schedule.get());

    // scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
    exeService.scheduleWithFixedDelay(() -> System.out.println(
        "scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit), Thread.currentThread() : "
            + Thread.currentThread()),
        5, 5, TimeUnit.SECONDS);

    // scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
    exeService.scheduleAtFixedRate(() -> System.out.println(
        "scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit), Thread.currentThread() : "
            + Thread.currentThread()), 5,
        10, TimeUnit.SECONDS);
  }
}
