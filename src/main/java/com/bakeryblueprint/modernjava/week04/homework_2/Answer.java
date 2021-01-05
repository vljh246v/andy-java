package com.bakeryblueprint.modernjava.week04.homework_2;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Answer {

  ConcurrentNavigableMap<Integer, String> navigableMap = new ConcurrentSkipListMap<>();
  AtomicInteger atomicInteger = new AtomicInteger(0);
  Runnable producer = () -> {

    // Q1 AtomicInteger 를 이용해서 순차적으로 증가된 키 값을 받아오세요
    int key = this.atomicInteger.getAndIncrement();

    // Q2 navigableMap 에 (순차적으로 증가된 키 값, 쓰레드 이름) 을 삽입하세요
    this.navigableMap.put(key, Thread.currentThread().getName());

    // Q3 해당 내용을 출력하시오 ( 추수 삽입된 내용과 console 에 찍힌 값이 동일한지 파악하기 위해)
    System.out.println("키 값 : " + key + ", 쓰레드 이름 : " + Thread.currentThread().getName());
  };

  public static void main(final String[] args) throws InterruptedException {
    final Answer answer = new Answer();
    answer.execute(10);
    TimeUnit.SECONDS.sleep(2);
    answer.printConcurrentNavigableMap();
  }

  public void execute(final int producersSize) {
    for (int i = 0; i < producersSize; i++) {
      new Thread(this.producer).start();
    }
  }

  public void printConcurrentNavigableMap() {
    System.out.println(this.navigableMap);
  }
}
