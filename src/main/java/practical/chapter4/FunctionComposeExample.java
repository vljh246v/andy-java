package practical.chapter4;

import java.util.function.Function;

public class FunctionComposeExample {

    public static void main(String[] args) {
        //Function 인터페이스의 compose 예
        Function<String, Integer> parseIntFunction =
                (String str) -> Integer.parseInt(str) + 1;
        Function<Integer, String> intToStrFunction =
                (Integer i) -> "String : " + Integer.toString(i);

        /*
        default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
            Objects.requireNonNull(before);
            return (v) -> {
                return this.apply(before.apply(v));
            };
        }
        */
        System.out.println(intToStrFunction.compose(parseIntFunction).apply("1000"));
    }




}
