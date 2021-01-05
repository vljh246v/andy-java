package com.bakeryblueprint.modernjava.week02_ssookie;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/*
 * 3.x, y를 인수로 받아 더하는 것을 출력하는 람다 표현식을 작성하시오.(BiConsumer 사용)
 */
public class BiConsumerExample {
    public static void executeBiConsumer(String params1, String params2, BiConsumer<String, String> biConsumer) {
        biConsumer.accept(params1, params2);
    }
    public static void main(String[] args) {
        BiConsumerExample.executeBiConsumer("Hello", "World",(params1, params2) ->  System.out.println(params1+","+params2 ));
    }
}
