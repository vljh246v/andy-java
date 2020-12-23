package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.Executor;

public class ch6_3 {

  public static void main(final String[] args) {
    new ch6_3().useExecutorInterface();
  }

  public void useRunnableInterface() {
    final Thread myThread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread());
      }
    });

    myThread.start();
  }

  public void useExecutorInterface() {
    final Executor e = new Executor() {
      @Override
      public void execute(final Runnable task) {
        // 1. runnable 인터페이스를 직접 실행
        task.run();

        // 2. Thread를 생성해서 실행
        new Thread(task).start();
      }
    };

    e.execute(() -> System.out.println(Thread.currentThread()));
  }

}
