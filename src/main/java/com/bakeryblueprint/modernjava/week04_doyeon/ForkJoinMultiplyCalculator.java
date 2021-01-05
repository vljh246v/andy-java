
package com.bakeryblueprint.modernjava.week04_doyeon;

import java.util.concurrent.*;
import java.util.stream.LongStream;

public class ForkJoinMultiplyCalculator extends RecursiveTask<Long> {

    public static final long THRESHOLD = 10;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinMultiplyCalculator(final long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinMultiplyCalculator(final long[] numbers, final int start, final int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {

        final long[] numbers = LongStream.rangeClosed(1, 10).toArray();

        final ForkJoinTask<Long> task = new ForkJoinMultiplyCalculator(numbers);
        final Long result = new ForkJoinPool().invoke(task);
        System.out.println(result);

    }

    @Override
    protected Long compute() {
        final int length = this.end - this.start;
        if (length <= ForkJoinMultiplyCalculator.THRESHOLD) {
            return this.computeSequentially();
        } else {
            final ForkJoinMultiplyCalculator leftTask = new ForkJoinMultiplyCalculator(this.numbers, this.start,
                    this.start + length / 2);
            leftTask.fork();

            final ForkJoinMultiplyCalculator rightTask = new ForkJoinMultiplyCalculator(this.numbers, this.start
                    + length / 2,
                    this.end);

            final Long rightResult = rightTask.compute();
            final Long leftResult = leftTask.join();

            return rightResult + leftResult;
        }
    }

    private long computeSequentially() {
        long sum = 1;
        for (int i = this.start; i < this.end; i++) {
            System.out.println(i);
            sum *= this.numbers[i];
        }
        return sum;
    }
}
