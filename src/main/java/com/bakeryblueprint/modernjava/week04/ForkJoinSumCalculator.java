package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

  public static final long THRESHOLD = 10_000;
  private final long[] numbers;
  private final int start;
  private final int end;

  public ForkJoinSumCalculator(final long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  private ForkJoinSumCalculator(final long[] numbers, final int start, final int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  public static void main(final String[] args) {
    final long[] numbers = LongStream.rangeClosed(0, 10_000_000).toArray();
    final ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
    final Long result = new ForkJoinPool().invoke(task);
    System.out.println(result);
  }

  @Override
  protected Long compute() {
    final int length = this.end - this.start;
    if (length <= ForkJoinSumCalculator.THRESHOLD) {
      return this.computeSequentially();
    } else {
      final ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(this.numbers, this.start,
          this.start + length / 2);
      leftTask.fork();

      final ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(this.numbers, this.start
          + length / 2,
          this.end);

      final Long rightResult = rightTask.compute();
      final Long leftResult = leftTask.join();

      return rightResult + leftResult;
    }
  }

  private long computeSequentially() {
    long sum = 0;
    for (int i = this.start; i < this.end; i++) {
      sum += this.numbers[i];
    }
    return sum;
  }
}
