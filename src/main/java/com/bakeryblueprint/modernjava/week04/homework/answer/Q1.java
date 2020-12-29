package com.bakeryblueprint.modernjava.week04.homework.answer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Q1 {

  public static void main(final String[] args) {

    final Q1 q1 = new Q1();
    q1.printMyNameByExecutorService(new MyTask("재현"));
    q1.printMyNameByExecutorService(new MyTask("재현"));
    q1.printMyNameByExecutorService(new MyTask("재현"));
    q1.printMyNameByExecutorService(new MyTask("재현"));
    q1.printMyNameByExecutorService(new MyTask("재현"));

    q1.printMyNameByScheduledExecutorService(new MyTask("재현"));
    q1.printMyNameByScheduledExecutorService(new MyTask("재현"));
    q1.printMyNameByScheduledExecutorService(new MyTask("재현"));
    q1.printMyNameByScheduledExecutorService(new MyTask("재현"));
    q1.printMyNameByScheduledExecutorService(new MyTask("재현"));


  }

  public void printMyNameByExecutorService(final MyTask task) {
    final ExecutorService executorService = Executors.newFixedThreadPool(5);
    executorService.execute(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
        task.task();
      } catch (final Exception e) {
        e.printStackTrace();
      }
    });

    executorService.shutdown();
  }

  public void printMyNameByScheduledExecutorService(final MyTask task) {
    final ScheduledExecutorService exeService = Executors.newScheduledThreadPool(5);

    exeService.schedule(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
        task.task();
      } catch (final Exception e) {
        e.printStackTrace();
      }
    }, 2, TimeUnit.SECONDS);
    exeService.shutdown();
  }

  public static class MyTask {

    private final String name;

    public MyTask(final String name) {
      this.name = name;
    }

    public void task() {
      System.out.println(
          "My Name : " + this.name + ", Thread Name : " + Thread.currentThread().getName());
    }
  }
}

