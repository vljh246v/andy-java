package com.bakeryblueprint.modernjava.week02_jaehyun;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Apple> appleList =
                Arrays.asList(new Apple("도연", "green", 101),
                        new Apple("현수", "red", 80),
                        new Apple("재엽", "blue", 300),
                        new Apple("재현", "black", 20));


        // 1번
        Predicate<Apple> applePredicate = it -> it.getWeight() > 100;
        List<Apple> result = appleList.stream()
                .filter(applePredicate)
                .collect(Collectors.toList());
        System.out.println(result);


        // 2번
        Consumer<List<Apple>> appleConsumer = System.out::println;
        printList(appleList, appleConsumer);


        // 3번
        BiConsumer<Integer, Integer> integerBiConsumer = (x, y) -> System.out.println(x + y);
        integerBiConsumer.accept(1, 2);

        //4 번
        IntToDoubleFunction integerDoubleIntToDoubleFunction = i -> Double.valueOf(i);
        System.out.println(integerDoubleIntToDoubleFunction.applyAsDouble(5));


        // 5번
        Supplier<String> stringSupplier = () -> "lamdaTest";
        System.out.println(stringSupplier.get());


        //6 번


    }
    public static void printList(List<Apple> appleList, Consumer<List<Apple>> listConsumer){
        listConsumer.accept(appleList);
    }
}
