package com.bakeryblueprint.modernjava.week02_ssookie;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/*
 * 1. Predicate를 사용하여 weight가 100보다 넘는 리스트를 반환하여 메서드 참조를 사용하여 출력하세요.
 */
public class PredicateExample {

//    public static boolean isValid(List<Apple> appleList, Predicate<Apple> predicate) {
//        return predicate.test((Apple) appleList);
//    }

    public static Predicate<Apple> isValid() {
		return (Apple a) -> a.getWeight() >=100;
	}

    // Predicate Interface Execution
    public static void main(String[] args) {
        List<Apple> appleList =
                Arrays.asList(new Apple("도연", "green", 101),
                              new Apple("현수", "red", 80),
                              new Apple("재엽", "blue", 300),
                              new Apple("재현", "black", 20));

        Predicate<Apple> predicateApple  = PredicateExample.isValid();
        for (Apple apple : appleList) {
            if(predicateApple.test(apple)){
                System.out.println(apple);
            }
        }
    }
}
