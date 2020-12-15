package practical.chapter5;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayToStreamExample {
    public static void main(String[] args) {
        Person[] personList =
                { new Person("정재엽",10),
                  new Person("임재현",11),
                  new Person("김현수",12),
                  new Person("류도연",13),
                };

        /*
        public static <T> Stream<T> stream(T[] array) {
            return stream((Object[])array, 0, array.length);
        }
        * */
        Stream<Person> stream = Arrays.stream(personList);

        Stream<Person> streamOf = Stream.of(
                new Person("정재엽",10),
                new Person("임재현",11));
    }
}
