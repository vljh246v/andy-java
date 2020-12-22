package com.bakeryblueprint.modernjava.week04.completable_future_example;

public class BestPriceFinderMain {

  private static final BestPriceFinder bestPriceFinder = new BestPriceFinder();

  public static void main(final String[] args) {
    final long start = System.nanoTime();
    BestPriceFinderMain.bestPriceFinder.findPrices("myPhone");
    final long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("Done in " + duration + "msecs");
  }

}
