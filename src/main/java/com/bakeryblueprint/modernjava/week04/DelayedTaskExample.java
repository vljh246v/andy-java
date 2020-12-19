package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTaskExample {

  public static void main(final String[] args) {
    final ScheduledExecutorService exeService = Executors
        .newSingleThreadScheduledExecutor();

    exeService.schedule(() -> System.out.println("TODO 1"), 5, TimeUnit.SECONDS);
    exeService.schedule(() -> System.out.println("TODO 2"), 10, TimeUnit.SECONDS);
    exeService.schedule(() -> System.out.println("TODO 3"), 15, TimeUnit.SECONDS);

    exeService.shutdown();
  }
}
