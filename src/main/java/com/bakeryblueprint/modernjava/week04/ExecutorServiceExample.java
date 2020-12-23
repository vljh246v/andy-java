package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

  public static void main(final String[] args) {
    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    executorService.execute(new MyTask("TODO 1"));
    executorService.execute(new MyTask("TODO 2"));
    executorService.execute(new MyTask("TODO 3"));

    executorService.shutdown();
  }

}


