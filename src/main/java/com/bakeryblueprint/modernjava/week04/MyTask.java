package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {

  private final String id;

  public MyTask(final String id) {
    this.id = id;
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println("Task ID : " + this.id + ", running... " + i);

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (final InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
