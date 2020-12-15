package practical.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReduceSumExample {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9);

        // Error 1. local variables referenced from a lambda expression must be final or effectively final
        // 외부에서 선언한 로컬 변수를 람다 표현식 내부에서 사용하기 위해서는 final이거나 fianl과 유사하여야함.
        // stream parallel 처리 때문아닐까?
/*
        int sum = 0;
        int count = 0;
        intList.stream().forEach(value -> { sum += value; count++;});
*/
/*
        // Error 2. cannot assign a value to final variable sum2까
        // forEach 문장에서는 계속 값이 변경되어야 하므로 final 변수를 사용할 수 없다.
        final int sum2 = 0;
        //intList.stream().forEach(value -> { sum2 += value;});
*/
        // for 문을 이용한 계산
        int sumTemp = 0;
        for(Integer i : intList) { sumTemp += i; }

        // forEach 이용
        int sum[] = {0};
        intList.stream().forEach(value -> sum[0] += value);

        // IntStream 이용 - 기본형 Stream에는 제공됨.
        int sum1 = intList.stream().mapToInt(Integer::intValue).sum();

        // Stream.collect 이용
        // collect(Collector) - Collectors는 Collector의 구현체 모음
        int sum2 = intList.stream().collect(Collectors.summingInt(Integer::intValue));

        // reduce 메서드 참조 정의
        int sum3 = intList.stream().reduce(0, Integer::sum);

        // reduce 람다 표현식으로 정의
        int sum4 = intList.stream().reduce(0, (x,y) -> x+y);

        // reduce 람다 표현식 + 병렬 처리
        int sum5 = intList.parallelStream().reduce(0, (x,y) -> x+y);
    }
}
