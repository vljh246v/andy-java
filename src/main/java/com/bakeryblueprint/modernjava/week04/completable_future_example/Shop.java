package com.bakeryblueprint.modernjava.week04.completable_future_example;

import java.util.Random;

public class Shop {

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
   * @param product
   * @return price
   * @apiNote 임의의 계산값 및 delay + 1초
   */
  private double calculatePrice(final String product) {
    Shop.delay();
    return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
  }

}
