package com.bakeryblueprint.modernjava.week04;

import java.util.Arrays;
import java.util.List;

public class ChangeParallelExample {

  public static void main(final String[] args) {
    final List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

    // 일반 스트림
    final int sum1 = intList.stream().reduce(0, Integer::sum);
    System.out.println(sum1);

    // 병렬 스트림
    final int sum2 = intList.parallelStream().reduce(0, Integer::sum);
    System.out.println(sum2);
  }

}
