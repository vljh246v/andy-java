package practical.chapter4;

import java.util.function.Consumer;

public class AndThenExample {

    //Consumer 조합 예제
    public static void main(String[] args) {
        Consumer<String> consumer =
                (String text) -> System.out.println("Hello : " + text);
        Consumer<String> consumerAndThen =
                (String text) -> System.out.println("Text Length is " + text.length());

        //Consumer 인터페이스 조합
        consumer.andThen(consumerAndThen).accept("Java");



    }
}
