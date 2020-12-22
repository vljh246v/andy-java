package com.bakeryblueprint.modernjava.week04.completable_future_example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BestPriceFinder {

  private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
      new Shop("LetsSaveBig"),
      new Shop("MyFavoriteShop"),
      new Shop("BuyItAll"),
      new Shop("ShopEasy"));

  public List<String> findPrices(final String product) {
    return this.shops.parallelStream()
        .map(it -> {
          return it.getShopName() + " price is " + it.getPrice(product);
        }).collect(Collectors.toList());
  }
}
