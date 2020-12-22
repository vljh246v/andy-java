package practical.chapter4.homework;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Homework1 {

    public static void main(String[] args) {
        List<Apple> appleList =
                Arrays.asList(new Apple("도연", "green", 101),
                        new Apple("현수", "red", 80),
                        new Apple("재엽", "blue", 300),
                        new Apple("재현", "black", 20));

        //Predicate를 사용하여 weight가 100보다 넘는 리스트를 반환하여 출력하세요.
        appleList.stream().filter(apple -> apple.getWeight() > 100)
            .forEach(System.out::println);

        //Consumer를 사용하여 사과 list 목록을 출력하는 람다 식을 만드세요.
        appleList.stream().forEach(System.out::println);
    }
}
