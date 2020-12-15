package practical.chapter4;

import java.util.function.Predicate;

public class PredicateExample {
    public static boolean isValid(String name, Predicate<String> predicate) {
        return predicate.test(name);
    }

    // Predicate 인터페이스 실행
    public static void main(String[] args) {
        System.out.println(PredicateExample.isValid("",(String name) -> name.isEmpty()));
    }
}
