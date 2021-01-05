package com.bakeryblueprint.modernjava.week04.homework_2;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Quiz {
  /*
  정답 example
  키 값 : 3, 쓰레드 이름 : Thread-8
  키 값 : 0, 쓰레드 이름 : Thread-2
  키 값 : 2, 쓰레드 이름 : Thread-4
  키 값 : 1, 쓰레드 이름 : Thread-3
  키 값 : 4, 쓰레드 이름 : Thread-9
  키 값 : 5, 쓰레드 이름 : Thread-0
  키 값 : 6, 쓰레드 이름 : Thread-1
  키 값 : 7, 쓰레드 이름 : Thread-5
  키 값 : 8, 쓰레드 이름 : Thread-6
  키 값 : 9, 쓰레드 이름 : Thread-7
  {0=Thread-2, 1=Thread-3, 2=Thread-4, 3=Thread-8, 4=Thread-9, 5=Thread-0, 6=Thread-1, 7=Thread-5, 8=Thread-6, 9=Thread-7}
   */

  ConcurrentNavigableMap<Integer, String> navigableMap = new ConcurrentSkipListMap<>();
  AtomicInteger atomicInteger = new AtomicInteger(0);
  Runnable producer = () -> {
    // Q1 AtomicInteger 를 이용해서 순차적으로 증가된 키 값을 받아오세요
    // Q2 navigableMap 에 (순차적으로 증가된 키 값, 쓰레드 이름) 을 삽입하세요

    // Q3 해당 내용을 출력하시오 ( 추수 삽입된 내용과 console 에 찍힌 값이 동일한지 파악하기 위해)
    System.out.println("키 값 : , 쓰레드 이름 : ");
  };

  public static void main(final String[] args) throws InterruptedException {
    final Quiz quiz = new Quiz();
    quiz.execute(10);
    TimeUnit.SECONDS.sleep(2);
    quiz.printConcurrentNavigableMap();
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
