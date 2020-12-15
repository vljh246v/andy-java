package practical.chapter5;

import java.util.stream.Stream;

public class StreamBuilderExample {
    public static void main(String[] args) {

        /*******Stream.Builder 내부 interface 선언부********
        public interface Builder<T> extends Consumer<T> {
            void accept(T var1);

            default Stream.Builder<T> add(T t) {
                this.accept(t);
                return this;
            }

            Stream<T> build();
        }
        **************************************************/
        /****Stream.Builder 구현체**************************
        static <T> Stream.Builder<T> builder() {
            return new StreamBuilderImpl();
        }
        **************************************************/
        Stream.Builder<String> builder = Stream.builder();
        builder.accept("1");
        builder.accept("2");
        builder.accept("3");
        builder.accept("4");
        builder.accept("5");
        builder.accept("6");
        builder.accept("7");
        builder.accept("8");
        builder.accept("9");
        builder.accept("0");

        builder.build().forEach(System.out::println);
    }
}
