package com.bakeryblueprint.modernjava.week04.completable_future_example;

import java.util.concurrent.Future;

public class ShopService {

  public static void main(final String[] args) {
    final Shop shop = new Shop("BestShop");
    final long start = System.nanoTime();
    final Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
    final long invocationTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("invocation returned after " + invocationTime + " msecs");

    ShopService.doSomethingElse();
    try {
      final double price = futurePrice.get();
      System.out.printf("Price is %.2f%n", price);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }

    final long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("Price returned after " + retrievalTime + " msecs");
  }

  private static void doSomethingElse() {
    System.out.println("Doing something else...");
  }

}
