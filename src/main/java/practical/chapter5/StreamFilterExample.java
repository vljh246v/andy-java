package practical.chapter5;

import java.util.Arrays;
import java.util.List;

public class StreamFilterExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //Stream<T> filter(Predicate<? super T> var1);
        list.stream().filter((Integer i) -> {
           if(i % 2 == 0) return true;
           else return false;
        })
                     .forEach(System.out::println);

        //Stream<T> filter(Predicate<? super T> var1);
        list.stream().filter((Integer i) -> i%2==0)
                     .forEach(System.out::println);
    }
}
