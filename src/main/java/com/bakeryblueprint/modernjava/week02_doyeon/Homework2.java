package com.bakeryblueprint.modernjava.week02_doyeon;

import com.bakeryblueprint.modernjava.week01_doyeon.Apple;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Homework2 {

    public static void main(String[] args) {

        // 1번문제
        List<Apple> appleList =
                Arrays.asList(new Apple("도연", "green", 101),
                        new Apple("현수", "red", 80),
                        new Apple("재엽", "blue", 300),
                        new Apple("재현", "black", 20));

        weightFilter(appleList, (apple) -> apple.getWeight() > 100).forEach(System.out::println);

        // 2번 문제
        printList(appleList, (apple) -> System.out.print(apple));

        // 3번 문제
        add(2, 3, (x, y) -> System.out.println(x + y));
        BiConsumer<Integer, Integer> addTwo = (x, y) -> System.out.println(x + y);
        addTwo.accept(1, 2);

        // 4번 문제
        intDoubleDivide(2, (x) -> {return x / 3;});

        // 5번 문제
        supplierExmple(() -> {return "lambda";});

    }

    public static List<Apple> weightFilter(List<Apple> list, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void printList(List<Apple> list, Consumer<Apple> c) {
        for (Apple apple : list) {
            c.accept(apple);
        }
    }

    public static void add(int x, int y, BiConsumer<Integer, Integer> consumer) {
        consumer.accept(x, y);
    }

    public static void intDoubleDivide(int x, IntToDoubleFunction intToDoubleFunction) {
        intToDoubleFunction.applyAsDouble(x);
    }

    public static String supplierExmple(Supplier supplier) {
        return (String) supplier.get();
    }
}
