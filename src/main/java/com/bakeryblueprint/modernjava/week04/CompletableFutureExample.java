package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    final Runnable mainTask = () -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (final Exception e) {
      }

      System.out.println("Main task : " + Thread.currentThread().getName());
    };

    final Runnable subTask = () -> System.out
        .println("Next task : " + Thread.currentThread().getName());

    // 사용자가 직접 Executor 지정
    final ExecutorService executor = Executors.newFixedThreadPool(2);

    CompletableFuture.runAsync(mainTask, executor).thenRun(subTask);
    CompletableFuture.runAsync(mainTask, executor).thenRun(subTask);
    CompletableFuture.runAsync(mainTask, executor).thenRun(subTask);
    CompletableFuture.runAsync(mainTask, executor).thenRun(subTask);
    CompletableFuture.runAsync(mainTask, executor).thenRun(subTask);
  }
}
