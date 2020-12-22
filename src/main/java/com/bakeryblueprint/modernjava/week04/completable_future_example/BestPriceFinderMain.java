package com.bakeryblueprint.modernjava.week04.completable_future_example;

public class BestPriceFinderMain {

  private static final BestPriceFinder bestPriceFinder = new BestPriceFinder();

  public static void main(final String[] args) {
    long start = System.nanoTime();
    BestPriceFinderMain.bestPriceFinder.findPrices("myPhone");
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("Done in " + duration + "msecs");

    // 왜 바꾸는 거지? -> CompletableFuture 형태를 사용하면 내가 정한 Executor 을 지정해 스레드 수를 정해줄 수 있음
    start = System.nanoTime();
    BestPriceFinderMain.bestPriceFinder.findPricesFuture("myPhone");
    duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("Done in " + duration + "msecs");
  }

}
