package practical.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamMatchExample {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //allMatch - 스트림의 데이터가 모두 일치
        boolean allMatchAnswer = numberList.stream().allMatch(number -> number < 10);

        //anyMatch - 스트림 데이터가 하나라도 일치
        boolean anyMatchAnswer = numberList.stream().anyMatch(number -> number < 10);

        //noneMatch - 스트림 데이터가 모두 일치하지 않음
        boolean noneMatchAnswer = numberList.stream().noneMatch(number -> number < 10);

        //스트림의 데이터 찾기 기능
        Optional<Integer> findFistAnswer = numberList.stream().parallel().filter(num -> num < 4).findFirst();
        System.out.println(findFistAnswer.get());

        Optional<Integer> findAnyAnswer = numberList.stream().parallel().filter(num -> num < 4).findAny();
        System.out.println(findAnyAnswer.get());
    }
}
