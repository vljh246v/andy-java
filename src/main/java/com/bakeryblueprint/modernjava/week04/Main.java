package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.Executors;

public class Main {

  public static void main(final String[] args) {

    Executors.newSingleThreadExecutor();
    Executors.newFixedThreadPool(10);
    Executors.newCachedThreadPool();
    Executors.newWorkStealingPool();
    Executors.unconfigurableExecutorService(Executors.newSingleThreadExecutor());

    Executors.newScheduledThreadPool(10);
    Executors.newSingleThreadScheduledExecutor();
    Executors.unconfigurableScheduledExecutorService(Executors.newSingleThreadScheduledExecutor());
  }
}
