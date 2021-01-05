package com.bakeryblueprint.modernjava.week04_ssookie;

import com.bakeryblueprint.modernjava.week04.ForkJoinSumCalculator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/*
Q.2 ForkJoinPool을 사용해서 1에서 20 까지 숫자를 곱하는 로직을 작성하세요.
 */
public class ForkJoinExample extends RecursiveTask<Long> {
    public static final long THRESHOLD = 4;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinExample(final long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinExample(final long[] numbers, final int start, final int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public static void main(final String[] args) {
        final long[] numbers = LongStream.rangeClosed(1, 20).toArray();
        final ForkJoinTask<Long> task = new ForkJoinExample(numbers);
        final Long result = new ForkJoinPool().invoke(task);
        System.out.println(result);
    }

    @Override
    protected Long compute() {
        final int length = this.end - this.start;
        if (length <= ForkJoinExample.THRESHOLD) {
            return this.computeSequentially();
        } else {
            final ForkJoinExample leftTask = new ForkJoinExample(this.numbers, this.start,
                    this.start + length / 2);
            leftTask.fork();

            final ForkJoinExample rightTask = new ForkJoinExample(this.numbers, this.start
                    + length / 2,
                    this.end);

            final Long rightResult = rightTask.compute(); // 쓰레드 낭비하지 않도록 => 재귀
            final Long leftResult = leftTask.join();

            return rightResult * leftResult;
        }
    }

    private long computeSequentially() {
        System.out.println(Thread.currentThread().getName());
        long multiply = 1;
        for (int i = this.start; i < this.end; i++) {
            multiply = multiply * this.numbers[i];
        }
        return multiply;
    }
}
