package com.bakeryblueprint.modernjava.week04;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InsuranceCalculator {

  public static void main(final String[] args) throws ExecutionException, InterruptedException {
    InsuranceCalculator.synchronousExample();
    InsuranceCalculator.asynchronousExample1();
    InsuranceCalculator.asynchronousExample2();
  }

  // 동기식 호출 방식
  public static void synchronousExample() {
    System.out.println("synchronousExample");
    final InsuranceCalculator calculator = new InsuranceCalculator();
    for (int i = 0; i < 5; i++) {
      System.out.printf("계산 차수 %s : %s\n", (i + 1), calculator.calculatePrice(null));
    }
  }

  // 비동기식 호출 example1 : 비즈니스 로직을 호출하는 부분을 비동기로 변경
  public static void asynchronousExample1() {
    System.out.println("asynchronousExample1");
    final ExecutorService service = Executors.newFixedThreadPool(5);
    final List<Future<Integer>> futureList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      final Future<Integer> submit = service.submit(() -> {
        return new InsuranceCalculator().calculatePrice(null);
      });
      futureList.add(submit);
    }

    futureList.forEach(it -> {
      try {
        System.out.printf("계산 결과 : %s\n", it.get());
      } catch (final InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });
    service.shutdown();
  }

  // 비동기식 호출 example2 : 비즈니스 로직을 비동기로 변경
  public static void asynchronousExample2() throws ExecutionException, InterruptedException {
    System.out.println("asynchronousExample2");
    final InsuranceCalculator calculator = new InsuranceCalculator();
    final List<Future<Integer>> futureList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      futureList.add(calculator.calculatePriceAsync(null));
    }

    for (int i = 0; i < 5; i++) {
      // 비즈니스로직에서 반환 한 Future 를 통해 값을 가지고 옴
      System.out.printf("계산 차수 %s : %s\n", (i + 1), futureList.get(i).get());
    }
  }

  public int calculatePrice(final Map condition) {
    final int price = 10000;

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (final Exception e) {
    }

    return price;
  }


  public Future<Integer> calculatePriceAsync(final Map condition) {
    final CompletableFuture<Integer> future = new CompletableFuture<>();

    new Thread(() -> {
      final int price = this.calculatePrice(condition);

      // 처리상태에 대한 레퍼런스를 등록한다.
      future.complete(price);
    }).start();

    return future;
  }

}
