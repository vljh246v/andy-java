package com.bakeryblueprint.modernjava.week03_ssookie;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamHomework {
    public static void main(String[] args) {

        /**
         * Question1. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하라.
         * Ex) 두개의 리스트[1,2,3]과 [3,4]가 주어지면 [(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
         */
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);

        // https://fenderist.tistory.com/47
        List<int[]> result = numbers1.stream()
                .flatMap(
                    i -> numbers2.stream()
                            .map(j -> new int[]{i, j})
                ).collect(Collectors.toList());
        for(int[] ks : result) {
            System.out.println("(" + ks[0] + "," + ks[1] + ")");
        }

        // jaehyun
        List<List<String>> result2 = numbers1.stream().map(integer -> {
            return numbers2.stream()
                    .map(integer1 -> "(" + integer + ", " + integer1 + ")")
                    .collect(Collectors.toList());
        }).collect(Collectors.toList());
        result2.forEach(System.out::println);

        // ssookie
        List<List<String>> result3 = numbers1.stream()
                .map(i -> {
                    return numbers2.stream()
                            .map(j -> "(" + i + "," + j + ")")
                            .collect(Collectors.toList());
                }).collect(Collectors.toList());
        List<String> flatList = result3.stream()    // https://futurecreator.github.io/2018/08/26/java-8-streams/
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        flatList.forEach(System.out::println);

        /**
         * Question2. map과 reduce 메서드를 이용해서 스트림의 갯수를 계산하라.
         * 참고로 count() 사용 금지.
         */
        List<String> listStr = Arrays.asList("정","재","엽","천","재");
        // 스트림 객체 생성
        Stream<String> streamStr = listStr.stream();
        int count = listStr.stream()
                .map(s -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);

    }
}
