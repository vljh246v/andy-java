package com.bakeryblueprint.modernjava.week04.completable_future_example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BestPriceFinder {

  private final List<Shop> shops = Arrays.asList(
      new Shop("shop1"),
      new Shop("shop2"),
      new Shop("shop3"),
      new Shop("shop4"),
      new Shop("shop5"),
      new Shop("shop6"),
      new Shop("shop7"),
      new Shop("shop8"),
      new Shop("shop9"),
      new Shop("shop10"),
      new Shop("shop11"),
      new Shop("shop12"),
      new Shop("shop13"));

  public List<String> findPrices(final String product) {
    return this.shops.parallelStream()
        .map(it -> it.getShopName() + " price is " + it.getPrice(product))
        .collect(Collectors.toList());
  }

  public List<String> findPricesFuture(final String product) {
    final List<CompletableFuture<String>> priceFutures =
        this.shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is "
                + shop.getPrice(product)))
            .collect(Collectors.toList());

    return priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
  }
}
