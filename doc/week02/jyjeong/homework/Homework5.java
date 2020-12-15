package practical.chapter4.homework;

import java.util.function.Supplier;

public class Homework5 {
    public static void main(String[] args) {
        //"lamdaTest" 문자열을 반환 받는 람다식을 작성하시오(Supplier interface 사용)
        Supplier<String> supplier = () -> "lamdaTest";
        System.out.println(supplier.get());
    }
}
