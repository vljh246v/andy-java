package com.bakeryblueprint.modernjava.week04.completable_future_example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import lombok.Data;

@Data
public class Shop {

  private final String shopName;

  public Shop(final String shopName) {
    this.shopName = shopName;
  }

  /**
   * @apiNote 지연을 흉내내는 메서드
   */
  public static void delay() {
    try {
      Thread.sleep(1000L);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param product
   * @return price
   * @apiNote 제품명에 해당하는 가격을 반환하는 메서드
   */
  public double getPrice(final String product) {
    return this.calculatePrice(product);
  }


  /**
   * @param product 상품명
   * @return Future<Double>
   * @apiNote getPrice 메서드의 비동기 형태
   */
  public Future<Double> getPriceAsync(final String product) {
    return CompletableFuture.supplyAsync(() -> this.calculatePrice(product));
  }

  /**
   * @param product
   * @return price
   * @apiNote 임의의 계산값 및 delay + 1초
   */
  private double calculatePrice(final String product) {
    Shop.delay();
//    if (new Random().nextBoolean()) {
//      throw new RuntimeException("product not available");
//    }
    return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
  }

}
