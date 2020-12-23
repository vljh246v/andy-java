package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PeriodTaskExample {

  public static void main(final String[] args) {

    final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    executorService.scheduleWithFixedDelay(
        new MyTask("Delayed 1"), 5, 10, TimeUnit.SECONDS
    );

    executorService.scheduleAtFixedRate(
        new MyTask("Rate 1"), 5, 10, TimeUnit.SECONDS
    );

    executorService.scheduleAtFixedRate(
        new MyTask("Rate 2"), 5, 10, TimeUnit.SECONDS
    );
  }

}
