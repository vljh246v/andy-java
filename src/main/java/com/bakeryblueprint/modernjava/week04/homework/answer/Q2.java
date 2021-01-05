package com.bakeryblueprint.modernjava.week04.homework.answer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class Q2 extends RecursiveTask<Long> {

  public static final long THRESHOLD = 2;
  private final long[] numbers;
  private final int start;
  private final int end;

  public Q2(final long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  private Q2(final long[] numbers, final int start, final int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  public static void main(final String[] args) {
    final long[] numbers = LongStream.rangeClosed(1, 10).toArray();
    final ForkJoinTask<Long> task = new Q2(numbers);
    final Long result = new ForkJoinPool().invoke(task);
    System.out.println(result);
  }

  @Override
  protected Long compute() {
    final int length = this.end - this.start;
    if (length <= Q2.THRESHOLD) {
      return this.computeSequentially();
    } else {
      final Q2 leftTask = new Q2(this.numbers, this.start,
          this.start + length / 2);
      leftTask.fork();

      final Q2 rightTask = new Q2(this.numbers, this.start
          + length / 2,
          this.end);

      final Long rightResult = rightTask.compute();
      final Long leftResult = leftTask.join();

      return rightResult * leftResult;
    }
  }

  private long computeSequentially() {
    long multiply = 1;
    for (int i = this.start; i < this.end; i++) {
      multiply *= this.numbers[i];
    }
    return multiply;
  }
}