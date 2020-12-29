package com.bakeryblueprint.modernjava.week04.homework;

import java.util.concurrent.TimeUnit;

public class Q3 {

  public static void main(final String[] args) {
    Q3.synchronousCallThreadName();
    Q3.asynchronousCallThreadName();
  }

  public static void synchronousCallThreadName() {
    final Q3 q3 = new Q3();

    final long start = System.nanoTime();
    for (int i = 0; i < 5; i++) {
      System.out.println("Thread Name : " + q3.returnThreadName());
    }
    final long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("Done in " + duration + "msecs");
  }

  public static void asynchronousCallThreadName() {
    final Q3 q3 = new Q3();
    final long start = System.nanoTime();
    for (int i = 0; i < 5; i++) {
      // 비동기식으로 returnThreadName 을 호출하는 로직을 작성 하시오
    }
    final long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("Done in " + duration + "msecs");
  }

  public String returnThreadName() {

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (final Exception e) {
    }
    return Thread.currentThread().getName();
  }
}
