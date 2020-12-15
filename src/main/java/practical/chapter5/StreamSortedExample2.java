package practical.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSortedExample2 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("정재엽", 10));
        personList.add(new Person("임재현", 11));
        personList.add(new Person("김현수", 12));
        personList.add(new Person("류도연", 13));
        personList.add(new Person("정재엽", 10));

        //Comparable compareTo 구현의 Reverse
        personList.stream().sorted(Comparator.reverseOrder())
                           .forEach(System.out::println);

        //나이순 정렬
        /* Comparator<? super T> var1 */
        /* Function<? super T, ? extends U> keyExtractor */
        personList.stream().sorted(Comparator.comparing(new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        }))
                .forEach(System.out::println);

        //나이순 정렬
        personList.stream().sorted(Comparator.comparing((Person p) -> p.getAge()))
                           .forEach(System.out::println);

        //이름순 정렬
        personList.stream().sorted(Comparator.comparing(Person::getName))
                           .forEach(System.out::println);

        List<Person> sortedList =
                personList.stream().sorted().collect(Collectors.toList());

    }
}
