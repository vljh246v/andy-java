package com.bakeryblueprint.modernjava.week04;

import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {

//    Executors.newSingleThreadExecutor();
//    Executors.newFixedThreadPool(10);
//    Executors.newCachedThreadPool();
//    Executors.newWorkStealingPool();
//    Executors.unconfigurableExecutorService(Executors.newSingleThreadExecutor());
//
//    Executors.newScheduledThreadPool(10);
//    Executors.newSingleThreadScheduledExecutor();
//    Executors.unconfigurableScheduledExecutorService(Executors.newSingleThreadScheduledExecutor());

    final CompletableFuture<Void> voidFuture = CompletableFuture
        .runAsync(() -> System.out.println("Runnable1~"))
        .thenRun(() -> System.out.println("Runnable2~"));
    final CompletableFuture<Integer> IntegerFuture = CompletableFuture.supplyAsync(() -> {
      System.out.println("Supplier~");
      return 1;
    }).thenApply(integer -> integer + integer);

    System.out.println(IntegerFuture.get());

    final Set<Integer> SetSpliterator = new HashSet<>();
    final Spliterator<Integer> spliterator = SetSpliterator.spliterator();
  }
}
