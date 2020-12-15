package practical.chapter4.homework;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Homework3 {
    public static void main(String[] args) {
        BiConsumer<Integer,Integer> biConsumer = (Integer a, Integer b) -> System.out.println(a+b);
        biConsumer.accept(1,2);

    }
}
