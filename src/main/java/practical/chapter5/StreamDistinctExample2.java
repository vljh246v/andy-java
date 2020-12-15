package practical.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamDistinctExample2 {
    /*
    1. personList의 stream 메서드를 이용해서 스트림 객체를 생성한다.
    2. filter 메서드가 호출된다.

    3. filter 메서드의 입력 파라미터인 Predicate 객체를 생성하기 위한 distinctByKey 메서드가 호출된다.
    4. distnctByKey 메서드에서 Predicate 객체를 생성한다.
    5. 생성된 Predicate 객체는 외부에서 선언된 Map<Object,Boolean> 객체를 참조한다. <- 어려운개념이네.
    6. 스트림 내부에서 Predicate 객체가 반복 실행되면서 true/false 값에 따라 필터링한다. <- 이것도.

    7. 필터링 결과 스트림에서 forEach 메서드가 호출된다.
    8. forEach 메서드의 파라미터로 전달된 함수 레퍼런스가 반복 실행된다.
    * */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> key) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        /*
        default V putIfAbsent(K key, V value) {
            V v = this.get(key);
            if (v == null) {
                v = this.put(key, value);
            }
            return v;
        }
        * */
        return (T t) -> seen.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
    }

    public void test() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("정재엽", 10));
        personList.add(new Person("임재현", 11));
        personList.add(new Person("김현수", 12));
        personList.add(new Person("류도연", 13));
        personList.add(new Person("정재엽", 10));

        personList.stream().filter(distinctByKey((Person p) -> (p.getName() + p.getAge())))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new StreamDistinctExample2().test();
    }

}
