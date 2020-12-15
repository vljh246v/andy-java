package practical.chapter4;

import java.util.function.Function;

public class FunctionAndThenExample {
    public static void main(String[] args) {
        Function<String, Integer> parseIntFunction =
                (String str) -> Integer.parseInt(str) + 1;
        Function<Integer, String> intToStrFunction =
                (Integer i) -> "String : " + Integer.toString(i);

        //System.out.println(parseIntFunction.apply("1000"));
        //System.out.println(intToStrFunction.apply(1000));

        //Function 객체 조합 후 실행
        /*
        default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (t) -> {
                return after.apply(this.apply(t));
            };
        }
        * */
        System.out.println(parseIntFunction.andThen(intToStrFunction).apply("1000"));
    }
}
