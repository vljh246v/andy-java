package com.bakeryblueprint.modernjava.week02_ssookie;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
 * 2. Consumer를 사용하여 사과 list 목록을 출력하는 람다 식을 만드세요.
 */
public class ConsumerExample {
    public static void executeConsumer(List<Apple> appleList, Consumer<Apple> consumer){
        for(Apple apple : appleList){
            // 메서드의 두 번째 인수로 전달된 람다 표현식 실행
            consumer.accept(apple);
        }
	}

    // Consumer Interface Execution
    public static void main(String[] args) {
        List<Apple> appleList =
                Arrays.asList(new Apple("도연", "green", 101),
                              new Apple("현수", "red", 80),
                              new Apple("재엽", "blue", 300),
                              new Apple("재현", "black", 20));

        ConsumerExample.executeConsumer(appleList, (Apple apple) -> System.out.println(apple));
    }
}
