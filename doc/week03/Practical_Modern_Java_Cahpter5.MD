# 2020.12.09
## Chapter5. 스트림 API
<hr>

### # 5.1 들어가며
- java.util.stream package 정의
- java.io.stream이랑 헷갈리면 안됩니다.
- 스트림은 데이터 컬렉션과 유사함
- 함수형 프로그래밍을 기반으로 자바8에서 새롭게 소개한 API
> <h3>Cahpter 5. Stream API Goal</h3>
> - 스트림 이해 <br>
> - 스트림 기본 사용 방법 <br>
> - 스트림 연산 이해 <br>
> - 리듀스 연산 <br>
> - 스트림 병렬 처리(Chapter 6. 병렬처리에서 다룸) <br>
<hr>
<br>

### # 5.2 스트림 인터페이스 이해
- 스트림(stream)은 프로그래밍 언어에서는 주로 어떤 데이터의 흐름을 말함
- 주로 Collection Framework나 이와 유사한 형태의 데이터를 처리할 때 도움을 줄 수 있는 자바8에서 새롭게 제안한 API
- 스트림 API 주목적은 람다 표현식과 메서드 참조 등의 기능과 결합해서 매우 복잡하고 어려운 데이터 처리 작업을 쉽게 조회하고 필터링하고 변환하고 처리할 수 있도록 하는 것

> <h3>스트림 API 이해 - List 클래스의 요소를 출력하는 방법</h3>
```java
Integer[] intArray = new Integer[] {1,2,3,4,5,6,7};
List numberList = Arrays.asList(intArray);

/*
 * List 출력 v1.0
 * 루프를 돌리기 위해서 인덱스 변수인 i를 사용함
 * 특정 위치에 따라 처리를 달리하고 싶을 때 이 방법을 사용(특별)
 * 인덱스를 이용한 List 처리는 좋은 선택이 아님.
 */
for(int i=0; i<numberList.size()l; i++) {
    System.out.println(numberList.get(i));
}

/*
 * List 출력 v2.0
 * 매 번 Iterator 객체를 생성하고 for 루프를 작성해야 하는 
 * 번거로움이 존재함
 * Iterator - Interface / List - Interface
 */
for(Iterator iter = numberList.iterator(); iter.hasNext(); ) {
    System.out.println(iter.next();
}

/*
 * List 출력 v3.0
 * 제너릭을 이용해서 List 객체를 출력할 때 for each 구문을 활용한 것
 * 자바5 도입
 */
for(Integer intValue : numberList) {
    System.out.println(intValue);
}

/*
 * List 출력 v4.0
 * 스트림 API를 이용해서 바로 결과를 출력하는 방식
 * 스트림 내부에서 개발자가 정의한 코드가 반복적으로 실행된다는 것을 의미한다.
 * 자바5 도입
 */
numberList.forEach(System.out::println);
```

#### # 5.2.1 스트림 인터페이스
- java.util.stream package에 정의되어 있음
- 대부분 인터페이스로 구성되어 있고 스트림 API를 사용하는 데 도움을 주는 유틸리티 클래스 몇 개가 존재함
- 스트림 API가 대부분 인터페이스로 구성되어 있는 이유는 구현체는 데이터의 원천에 해당하는 컬렉션 프레임워크 기반의 클래스에 위임하고 있기 때문이다.
- 스트림은 람다 표현식이나 메서드 참조를 통해 구체적인 구현체를 전달받아 동작

> <h3># BaseStream - 스트림에서 가방 기본이 되는 인터페이스</h3>
> - T : 스트림에서 처리할 데이터의 타입 <br>
> - S : BaseStream을 구현한 스트림 구현체를 의미한다. BaseStream 외에도 스트림을 자동으로 종료하기 위한 AutoCloseable 인터페이스도 구현되어야함.<br>
> - S타입으로 지정한 타입은 AutoCloseable 인터페이스의 close 메서드를 "반드시?" 구현해야 한다. 이는 try-resource 구문으로 스트림 처리가 가능하고 자동으로 종료 가능하도록 하기 위해서이다.<br>
> - https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html<br>
> - search : closing.<br>
> - 스트림 객체를 병렬/순차 방식으로 생성하고 최종적으로 종료하기 위한 명세<br>
> - 스트림API 최상위 인터페이스 BaseStream.<br>
```java
public interface BaseStream<T, S extends BaseStream<T, S>> extends AutoCloseable {
    Iterator<T> iterator();
    Spliterator<T> spliterator();
    boolean isParallel();
    S sequential();
    S parallel();
    S unordered();
    S onClose(Runnable var1);
    // 이거 실제 구현체가 어디인지 찾지 못했음.
    void close();
}
```

> <h3># Stream Interface</h3>
> - BaseStream 인터페이스와 Stream 인터페이스에 정의되어 있는 메서드들의 상당수가 리턴 타입이 Stream, 나머지는 void형이라는 것이다.<br>
> - 리턴 타입이 Stream인 메서드들은 리턴 결과를 이용해 데이터를 중간에 변형, 필터링한 후 다시 Stream 객체를 만들어 결과를 리턴한다. 이러한 작업을 반복적으로 할 수 있으며 중간 연산 메서드 라고 부른다.<br>
> - 리턴 타입이 없는 void형 메서드들은 주로 Stream을 이용해서 데이터를 최종적으로 소비한다. 이러한 메서드를 최종 연산 메서드라고 부른다.<br>
> - list.stream().map(String::toUpperCase).forEach(System.out::println);<br>
> - Stream 객체는 불변성이 특징이라는 점 <br>
> - 중간 연산 작업과 함께 병렬 처리가 가능하기 때문에 데이터의 정합성을 확보하기 위해서 Stream 객체의 메서드 호출 결과로 리턴 받은 Stream 객체는 완전히 새롭게 생성한 데이터로 사용한다.
```java
public interface Stream<T> extends BaseStream<T, Stream<T>> {
    // concat
    static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        Spliterator<T> split = new java.util.stream.Streams.ConcatSpliterator.OfRef(a.spliterator(), b.spliterator());
        Stream<T> stream = StreamSupport.stream(split, a.isParallel() || b.isParallel());
        return (Stream)stream.onClose(Streams.composedClose(a, b));
    }

    //collect
    <R> R collect(Supplier<R> var1, BiConsumer<R, ? super T> var2, BiConsumer<R, R> var3);

    //count
    long count();

    //distinct
    Stream<T> distinct();

    //filter
    Stream<T> filter(Predicate<? super T> var1);

    //forEach
    void forEach(Consumer<? super T> var1);

    //limit
    Stream<T> limit(long var1);

    //reduce
    T reduce(T var1, BinaryOperator<T> var2);
    Optional<T> reduce(BinaryOperator<T> var1);
    <U> U reduce(U var1, BiFunction<U, ? super T, U> var2, BinaryOperator<U> var3);

    //skip
    Stream<T> skip(long var1);

    //sorted
    Stream<T> sorted();
    Stream<T> sorted(Comparator<? super T> var1);

    //toArray
    Object[] toArray();
    <A> A[] toArray(IntFunction<A[]> var1);
}
```

#### # 5.2.2 기본형 스트림 인터페이스
- 컬렉션 프레임워크와 마찬가지로 스트림 API 역시 데이터가 객체라는 것을 기본 전제로 설계됨
- BaseStream 인터페이스에서 확인한 것과 같이 제네릭 타입을 명시적으로 지정
- 기본 형 데이터인 int, long, double 등의 값을 처리하면 오토 박싱/언박싱이 발생하여 성능면에서 취약해진다.
- 박싱/언박싱 발생하지 않도록 기본 자료형 스트림 인터페이스를 제공한다.
> <h3># 기본 자료형 스트림 </h3>
> - DoubleStream extends BaseStream<br>
> - IntStream extends BaseStream<br>
> - LongStream extends BaseStream<br>
<hr>

### # 5.3 스트림 객체 생성
- 스트림 객체를 생성하는 방법
<br><br>

#### # 5.3.1 스트림 객체 생성 이해
- 스트림은 그 자체로 데이터를 생성하고 관리하는 것이 아니라 데이터 원본에서 객체를 생성한다.
- 일반적으로 Collection Framework / Array에서 생성함
- 자바8 버전부터 다음과 같이 default 메서드가 추가된 것을 확인할 수 있다.
```java
public interface Collection<E> extends Iterable<E> {
    // Collection Interface 구현체에서 stream 사용가능.
    // ArrayList, LinkedList, SortedList 등..
    default Stream<E> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    // Stream을 이용한 병렬처리. 6장에서 다룸
    default Stream<E> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }
}
```
- Stream은 한번 사용하고 나면 다시 사용할 수 없다. 스트림은 데이터의 흐름이며 한번 흘러간 데이터는 다시 돌아오지 않는다.
- Stream 인터페이스의 메서드 중 void를 리턴하는 메서드를 호출하면 전체 스트림 데이터를 처리하기 때문에 데이터를 모두 소모하고 종료된다.
- 스트림은 데이터 원천을 참조하는 형태이기 때문에 총 건수를 계산하기 위해서는 데이터를 전부 읽어들인 후에야 그 결과를 리턴할 수 있다.
- 그래서 이러한 연산은 성능도 떨어지고 스트림을 다시 재활용할 수 없음.
- 스트림 데이터를 다 소모하게 만드는 메서드를 "최종 연산"이라고 한다.
```java
Stream<String> firstStream = firstList.stream();
// long count(); Stream 최종연산
firstStream.count();

//Stream<T> limit(long var1);
Stream<String> limitedStream = firstStream.limit(5);

// Result
//IllegalStateException: stream has already been operated upon or closed
```
- stream을 다시 선언해야 오류를 피할 수 있다.
- 빌더 패턴과 유사하게 메서드를 계속해서 연결해 최종 연산자인 forEach 메서드로 그 결과를 출력.(파이프라인)
- stream 메서드와 limit 메서드는 모두 스트림 객체를 리턴
- 대부분의 스트림 처리는 함축된 코드처럼 필요한 만큼 계속해서 중간 연산을 적용한 다음 최종 연산으로 종료시키는 방법을 사용한다.(파이프라인)
```java
Stream<String> firstStream = firstList.stream();

// Success
firstList.stream().count();
firstList.stream().limit(5).forEach(System.out::println);
```

#### # 5.3.2 스트림 빌더
- 공감ok ~ 스트림 API를 배운 후 가장 처음 자신의 코드에 반영하려 할 부분은 컬렉션 프레임워크에 포함된 데이터를 처리하는 용도
- 이미 스트림 객체를 생성하는 시점은 컬렉션 프레임워크로 선언된 경우가 대부분이다. 그렇게 사용을 거의 대부분 한다.
- 스트림은 데이터를 소모하는 역할만 하고 데이터를 생성하는 역할을 수행하지 않는다.(올바른 사용)
- 하지만 예외는 있다. 데이터를 직접 생성하기 위한 기능제공 -> 스트림 빌더
- 스트림 빌더 역시 스트림 객체와 마찬가지로 한 번 사용하고 나면 재사용X
- 데이터를 여러 번 재활용하거나 데이터 유지가 필요하면 스트림빌더X, 컬렉션O
```java
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
        builder.add("4")
               .add("5")
               .build().forEach(System.out::println);
    }
}
```
<hr>

### # 5.4 스트림 연산 이해
- 파이프라인 중요.
- Unix/Linux 명령어는 파이프라인을 통해 서로 연결하여 편리하게 사용함.
- Ex) ls -alR | grep *.txt, ps -ef | grep [ProcessName]
- 스트림 API 기반의 연산 작업을 선호하는 이유는 파이프라인과 유사한 개념으로 기능을 조합할 수 있기 때문이다.
- Transaction -> Filter(Predicate) -> Sorted(Comparator) -> Map(Function) -> Collect
- 이러한 과정을 단 한 줄의 코드로 표현할 수 있다.
- 연산 작업을 수행해서 또 다른 스트림 객체를 리턴하는데 이를 "중간연산"
- 스트림 데이터를 모두 소모하고 스트림을 종료하는 이를 "최종연산"
- java.util.stream Package / 대부분 함수형인터페이스 이용.
> <h3># 스트림 객체의 생명주기</h3>
> - 스트림 객체 생성 : list.stream() <br>
> - 중간 연산 단계 : list.stream().map().sorted() <br>
> - 최종 연산 단계 : list.stream().map().sorted().forEach() <br>
<br>

#### # 5.4.1 중간 연산
- 중간 연산 관련 메서드의 리턴 타입은 Stream이다.
- 스트림의 결과를 연결할 수 있다.
- p131 표 5.4 주요 중간 연산 메서드

#### # 5.4.2 최종 연산
- 최종 연산 관련 메서드의 리턴 타입은 Not Stream이다.
- 데이터를 처리함
- 재사용할 수 없다.
- p132 표 5.5 주요 최종 연산 메서드
<hr>

### # 5.5 주요 스트림 연산 상세
- 스트림 API에서 제공하는 연산 메서드들에 대해서 상세하게 설명
<br><br>

#### # 5.5.1 데이터 필터링
> <h3># 데이터 필터링과 관련된 메서드</h3>
- filter(Predicate<? super T> var1);<br>
- distinct();<br>
- limit();<br>
- skip();<br>
<hr>

- Stream<T> filter(Predicate<? super T> var1);
```java
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
```
- Stream<T> distinct();
> <h3># distinct 메서드는 성능을 저하시킬 수 있다.</h3>
> - 스트림 병렬 처리시, 데이터 중복제거를 위해 여러 스레드에 분산해 놓은 데이터를 동기화해서 비교해야 하므로 병렬x, 순차o <br>
> - 성능에 취약하다.<br><br>
> <h3># 중복 제거가 안될 수도 있다.</h3>
> - 스트림 항목의 중복 여부를 확인하기 위해 equals 메서드가 내부적으로 호출<br>
> - 클래스 객체를 비교할 경우 신뢰성이 떨어진다.<br><br>
> -> 이를 충분히 고려하여 distinct를 사용해야한다.
- 객체 비교 distinct()를 사용하기 위해서는 equals(), hashCode() Method를 @Override 해야한다.
```java
class Person {
    //...........
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Person) {
            Person p = (Person)o;
            if(p.getName().equals(this.getName()) && p.getAge() == this.getAge()) return true;
            else return false;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return (name + age).hashCode();
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("정재엽", 10));
        personList.add(new Person("임재현", 11));
        personList.add(new Person("김현수", 12));
        personList.add(new Person("류도연", 13));
        personList.add(new Person("정재엽", 10));

        // # 이거 왜 안되는데 ?
        // distinct() - 내부적으로 Object.equals로 구현됨
        // public boolean equals(Object obj){this == obj};
        // hashCode / equals 함께 Override ㄱ
        personList.stream().distinct().forEach(System.out::println);
    }
}
```
- 만약 Person 클래스처럼 equals, hashCode 메서드를 오버라이드할 수 없다면 다른 방법으로 제거해야함.
- 이해하는데 쉽지 않음.
```java
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

        // filter 한번 실행
        // distinctByKey 메서드 한번 실행 - 스트림개수 반복호출X
        // 따라서, 메서드 내부 Map 참조가능
        // 객체의 범위 밖에 있는 객체를 활용할 수 있는 것이 익명클래스의 장점
        personList.stream().filter(distinctByKey((Person p) -> (p.getName() + p.getAge())))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new StreamDistinctExample2().test();
    }
}
```
- limit(), skip()
- limit의 경우 스트림의 데이터 중 정숫값만큼 데이터의 개수를 제한
- skip 메서드는 주어진 입력 파라미터의 값만큼 데이터를 건너뛰라는 의미

#### # 5.5.2 데이터 정렬
- 스트림 API에서는 포함하고 있는 항목에 대한 정렬 기능을 제공
> <h3># 데이터 정렬과 관련된 메서드</h3>
- sorted()<br>
- sorted(Comparator<? super T> comparator)<br>
<hr>

- sorted() - sorted 대상 Comparable 구현을 해야 사용가능함.
- Person이 Comparable을 구현 -> Success
- 구현X -> Error
```java
/*
public final Stream<P_OUT> sorted() {
    return SortedOps.makeRef(this);
}

static <T> Stream<T> makeRef(AbstractPipeline<?, T, ?> upstream) {
    return new SortedOps.OfRef(upstream);
}

OfRef(AbstractPipeline<?, T, ?> upstream) {
    super(upstream, StreamShape.REFERENCE, StreamOpFlag.IS_ORDERED | StreamOpFlag.IS_SORTED);
    this.isNaturalSort = true;
    Comparator<? super T> comp = Comparator.naturalOrder();
    this.comparator = comp;
}

static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
    return NaturalOrderComparator.INSTANCE;
}
*/
personList.stream().sorted().forEach(System.out::println);
```
- sorted(Comparator<? super T> comparator)
- 사용하는 클래스를 수정하지 못하는 경우 Comaprator 객체를 받아 사용함.
- 조금 더 유연하게 사용이 가능하다.
- Comparator Interface는 compare(T o1, T o2)만 구현하여 사용
- Comparator Interface 내 자바 8부터 comparing, reverseOrder 등 메서드가 많이 추가되었다. 아주 유용하다.(default, static Method)
```java
public class StreamSortedExample2 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("정재엽", 10));
        personList.add(new Person("임재현", 11));
        personList.add(new Person("김현수", 12));
        personList.add(new Person("류도연", 13));
        personList.add(new Person("정재엽", 10));

        //Comparable compareTo 구현의 Reverse
        /*
        static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
            return Collections.reverseOrder();
        }
        */
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
    }
}
```
- Comparator의 comparing 메서드는 객체를 정렬할 때 많이 사용하는 방법
- 람다 표현식이나 메서드 참조를 이용해서 기준값 지정가능

#### # 5.5.3 데이터 매핑
- 스트림에 포함되어 있는 데이터 항목을 다른 값으로 변환하는 것을 의미한다.
> <h3># 데이터 매핑과 관련된 메서드</h3>
- map(Function<? super T, ? extends R> mapper)<br>
- mapToDuble(ToDoubleFunction<? super T> mapper)<br>
- mapToInt(ToIntFunction<? super T> mapper)<br>
- mapToLong(ToLongFunction<? super T> mapper)<br>
<hr>

- map(Function<? super T, ? extends R> mapper)
- Person 타입의 스트림을 String 타입의 스트림으로 변환하여 출력
```java
//Stream<Person> -> Stream<String>으로 변환
Stream<String> personStream = 
    personList.stream().map((Person p) -> p.toString());
personStream.forEach(System.out::println);
```

- mapToInt(ToIntFunction<? super T> mapper)
- 박싱/언박싱으로 성능과 관련되어있음.
- 또한 타입이 정해져있기에 가독성이 좋음
```java
//Stream<Person> -> Stream<String>으로 변환
IntStream intStream = 
    personList.stream().mapToInt((Person p) -> p.getAge());
intStream.forEach(System.out::println);
```

#### # 5.5.4 데이터 반복 처리
- 반복적으로 데이터를 읽어 최종적으로 원하는 목적으로 저장하거나 전달하거나 소모하는 작업을 수행함
- 이건 너무 많이 봐서 관련 예제가 필요없다.
> <h3># 데이터 반복 처리 관련 메서드</h3>
- forEach(Consumer<? super T> action)<br>
- forEachOrdered(Consumer<? super T> action)<br>
<hr>

<h2>대신 Map Interface forEach 메서드를 보자!</h2>

- 스트림 인터페이스 forEach 메서드와 이름과 기능이 유사하지만 서로 다름.
- java.util.Map 인터페이스 forEach 메서드가 추가되어 있음
> <h3># Map.forEach(BiConsumer<? super K,? super V> action</h3>
> - 입력 파라미터로 Biconsumer 사용(Key,Value)<br>
> - 리턴타입이 void이고 default 메서드를 사용하였다.<br>
> - 기존 Map Interface에 추가해야했으므로..<br>
```java
public class MapIteratorExample {
    public static void main(String[] args) {
        Map<String, Person> map = new HashMap<>();
        map.put("1",new Person("정재엽", 10));
        map.put("2",new Person("김현수", 11));
        map.put("3",new Person("류도연", 12));
        map.put("4",new Person("임재현", 13));
        map.put("5",new Person("정재엽", 10));

        // Iterator 이용
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(
                    String.format("Key : %s, Value : %s",key,map.get(key)));
        }

        // Map Entry 이용
        for(Map.Entry<String, Person> element : map.entrySet()) {
            System.out.println(
                    String.format("Key : %s, Value : %s", element.getKey(), element.getValue()));
        }

        // Map의 keySet 이용
        for(String key : map.keySet()) {
            System.out.println(
                    String.format("Key : %s, Value : %s",key,map.get(key)));
        }

        // forEach 이용
        map.forEach((key,value) ->
                String.format("Key : %s, Value : %s",key,value));

        map.forEach(new BiConsumer<String, Person>() {
            @Override
            public void accept(String s, Person person) {
                String.format("Key : %s, Value : %s",s,person.toString());
            }
        });

    }
}
```
- forEach를 사용하면 개발자의 책임이 줄어든다.
- Map 객체는 외부에서 수정이 가능한 가변 객체이지만, forEach사용시 변하지 않는다.
- 비록 Map의 forEach가 스트림 API는 아니지만, 그냥 비슷하다. 사용하면 좋다.
<hr>

#### # 5.5.5 컬렉션으로 변환
- 스트림은 데이터 처리 후 다시 재사용할 수 없고 스트림을 새로이 만들어야한다.
- Collection Framework는 데이터를 재사용할 수 있다.
- 중간 연산자를 사용하여 스트림 데이터 변환/필터링 한 후 collect 메서드를 이용해서 컬렉션 프레임워크 객체로 변환할 수 있다.
> <h3># 컬렉션 변환 메서드</h3>
- collect(Supplier<R> supplier, Biconsumer<R,? super T> accumulator, Biconsumer<R,R> combiner)<br>
- collect(Collector<? super T,A,R> collector)<br>
<hr>

- collect(Collector<? super T,A,R> collector)
- Collector Interface는 java.util.stream Package에 포함

> T : 리듀스 연산의 입력 항목<br>
> A : 리듀스 연산의 변경 가능한 누적값<br>
> R : 리듀스 연산의 최종 결과 데이터 타입<br>
- Collector Interface를 구현한 유틸리티 클래스로 Collectors를 제공
- Collector 인터페이스 기반으로 많이 사용할 만한 것들을 사전에 정의해서 유틸리티 형태로 제공함.
- toList() = ArrayList형태로 반환
```java
List<Person> sortedList =
    personList.stream().sorted().collect(Collectors.toList());
```

- TreeSet형태로 반환
```java
Set<String> set =
    people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
```

- LinkedList형태로 반환
```java
List<String> set =
    people.stream().map(Person::getName).collect(Collectors.toCollection(LinkedList::new));
```

- 문자열 합치기
- CSV 파일 형태로 저장하는 등의 작업을 할 때 사용
```java
String joined = things.stream().map(Object::toString).collect(Collecotrs.joining(", "));
```

- 스트림 항목 특정 값의 합 리턴
```java
int total = employees.stream().collect(Collectors.summingInt(Employ::getSalary));
```

- 항목의 특정 데이터를 기준으로 분류해서 Map 객체로 변환
```java
Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));
```

- Boolean 값으로 데이터를 분류한 후 Map 객체로 변환
- 파티션 기능은 스트림의 데이터를 조건에 맞는지 여부에 따라 두 가지로 분류해서 Map으로 리턴하므로 리턴 객체 타입이 Map<Boolean, List<?>>로 고정
```java
Map<Boolean, List<Student>> passingFailing = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
```
<hr>

### # 5.6 기타 스트림 생성 방법
- 스트림은 대부분 Collection Framework인 List 객체로부터 stream 메서드를 호출해서 스트림 객체를 생성
- 이외에도 스트림 객체를 생성할 수 있는 추가 방법들을 제공함.
- Collection.stream() : 일반적인 Collection 인터페이스 stream 호출
```java
default Stream<E> stream() {
    return StreamSupport.stream(this.spliterator(), false);
}
```
- Arrays.stream(Object[]) : 배열을 스트림 객체로 변환
- Arrays 클래스 스트림 관련 주요 메서드
- stream, parallelSort, parallelPrefix, spliterator
```java
public class ArrayToStreamExample {
    public static void main(String[] args) {
        Person[] personList =
                { new Person("정재엽",10),
                  new Person("임재현",11),
                };

        /*
        public static <T> Stream<T> stream(T[] array) {
            return stream((Object[])array, 0, array.length);
        }
        * */
        Stream<Person> stream = Arrays.stream(personList);
    }
}
```
- Stream 인터페이스에서는 스트림 객체를 생성할 수 있는 of 메서드를 제공함
- of 메서드는 자바 8 이후부터 객체를 생성하는 메서드 명명 규칙으로 활용됨
```java
public class ArrayToStreamExample {
    public static void main(String[] args) {

        /* 내부적으로 Arrays.stream
        @SafeVarargs
        static <T> Stream<T> of(T... values) {
            return Arrays.stream(values);
        }
        */
        Stream<Person> stream = Stream.of(
                  new Person("정재엽",10),
                  new Person("임재현",11));
    }
}
```
<hr>

### # 5.7 추가 스트림 연산들
#### # 5.7.1 데이터 평면화
- Collection 기반의 데이터들은 주로 배열이나 이름, 값으로 구성된 맵형태를 가지고 있다.
- 하지만 상당히 많은 데이터가 배열 내부에 또 배열이 있거나, 맵 데이터에서 값이 배열일 수도 있다.
- Ex) List<[]>, Map<String, List>
- 이러한 다차원의 데이터가 있는 환경에서 필요한 기능 중 하나가 데이터 평면화이다.
- 다중 배열 형태의 데이터를 필터링하거나 검색하거나 특정 조건의 작업을 수행해서 데이터를 처리해야 할 경우 유용하다.
- Stream.flatMap(Function)을 이용하여 리스트 혹은 배열의 값을 1차원 데이터로 변환한다.
```java
public class StreamFlatMapExample {
    public static void main(String[] args) {
        String[][] rawData = new String[][] {
                {"a","b"},
                {"c","d"},
                {"e","a"},
                {"a","h"},
                {"i","j"},
        };

        List<String[]> rawList = Arrays.asList(rawData);

        rawList.stream()
        // flatMap -> {a,b,c,d,e,a,a,h,i,j}
               .flatMap((String[] array) -> Arrays.stream(array))
               .filter((String data) -> "a".equals(data.toString()))
               .forEach((String data) -> System.out.println(data));
    }
}
```
#### # 5.7.2 데이터 검색
- 필터링 : 고정된 유형으로 데이터의 참과 거짓을 판별해서 원하는 데이터 집합을 생성
- 검색 : 특정한 패턴에 맞는 데이터를 조회
- 하나의 패턴이 아니라 여러 개의 패턴을 조합해서 자기가 원하는 데이터를 정확히 검색할 수 있다.
- 쇼트 서킷(Short Circuit)
- Stream API : Match(allMatch, anyMatch, noneMatch)
- Stream API : Find(findFirst, findAny)
> <h3> # 참고 클래스 Optional</h3>
> http://www.tcpschool.com/java/java_stream_optional
```java
public class StreamMatchExample {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //allMatch - 스트림의 데이터가 모두 일치
        boolean allMatchAnswer = numberList.stream().allMatch(number -> number < 10);

        //anyMatch - 스트림 데이터가 하나라도 일치
        boolean anyMatchAnswer = numberList.stream().anyMatch(number -> number < 10);

        //noneMatch - 스트림 데이터가 모두 일치하지 않음
        boolean noneMatchAnswer = numberList.stream().noneMatch(number -> number < 10);

        //스트림의 데이터 찾기 기능
        //findfirst - 첫번째값
        Optional<Integer> findFistAnswer = numberList.stream().parallel().filter(num -> num < 4).findFirst();
        System.out.println(findFistAnswer.get());
        
        //findAny - 아무거나 조회첫번째값
        Optional<Integer> findAnyAnswer = numberList.stream().parallel().filter(num -> num < 4).findAny();
        System.out.println(findAnyAnswer.get());
    }
}
```
<hr>

### # 5.8 리듀스 연산
- 최종연산 : 스트림 데이터를 즉시 처리 + 스트림 데이터 모두 소모 후 처리
- 데이터를 최종적으로 다 확인해서 결과값을 도출하는 최종 연산을 자바 스트림 API에서 "리듀스 연산"이라고 부른다.
- 스트림에서는 reduce 메서드를 제공하므로 리듀스 연산이라고 하면 reduce 메서드 자체를 의미하는 경우가 많다.
- 데이터를 계속 접어서 더는 접을 수 없을 때까지 진행한다는 의미로 '폴드(fold)'라고도 부른다.

#### # 5.8.1 합계를 구하는 방법들
- 이미 for 루프를 이용해서 reduce 연산 처리가 가능하다.
- 그럼에도 불구하고 왜 스트림 API Reduce 가 필요한가?
- 병렬 처리를 해도 안전하게 계산되며 빠르다.
- 합계 구하는 방법 예제
```java
public class ReduceSumExample {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9);

        // Error 1. local variables referenced from a lambda expression must be final or effectively final
        // 외부에서 선언한 로컬 변수를 람다 표현식 내부에서 사용하기 위해서는 final이거나 fianl과 유사하여야함.
        // stream parallel 처리 때문아닐까?
/*
        int sum = 0;
        int count = 0;
        intList.stream().forEach(value -> { sum += value; count++;});
*/
/*
        // Error 2. cannot assign a value to final variable sum2까
        // forEach 문장에서는 계속 값이 변경되어야 하므로 final 변수를 사용할 수 없다.
        final int sum2 = 0;
        //intList.stream().forEach(value -> { sum2 += value;});
*/
        // for 문을 이용한 계산
        int sumTemp = 0;
        for(Integer i : intList) { sumTemp += i; }

        // forEach 이용
        int sum[] = {0};
        intList.stream().forEach(value -> sum[0] += value);
        
        // IntStream 이용 - 기본형 Stream에는 제공됨.
        int sum1 = intList.stream().mapToInt(Integer::intValue).sum();
        
        // Stream.collect 이용
        // collect(Collector) - Collectors는 Collector의 구현체 모음
        int sum2 = intList.stream().collect(Collectors.summingInt(Integer::intValue));
    }
}
```

#### # 5.8.2 리듀스 연산 이해
- 모든 스트림 데이터를 처리해서 데이터를 도출해야 한다면 리듀스 메서드를 우선 고려해볼만 하다.
```java
// 메서드의 첫 번째 인수는 초깃값을 의미한다.
// 메서드의 두 번째 인수는 BinaryOperator 함수형 인터페이스이다.
reduce(T identity, BinaryOperator<T> accumulator)
```
- Reduce 이용 합계 구하는 예제
```java
public class ReduceSumExample {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        
        // reduce 메서드 참조 정의
        int sum3 = intList.stream().reduce(0, Integer::sum);
        
        // reduce 람다 표현식으로 정의
        int sum4 = intList.stream().reduce(0, (x,y) -> x+y);
        
        // reduce 람다 표현식 + 병렬 처리
        int sum5 = intList.parallelStream().reduce(0, (x,y) -> x+y);
    }
}
```
- Reduce 어떤식으로 동작하는지 디버깅하는 예제
- 이 예제를 파악하며 합계뿐만이 아니라 사용자정의로 다른 기능을 응용하여 제대로 사용하는 것을 저자가 원한다.
- 그래서 5.8.3이 리듀스 연산 응용이다.
- 초기값 x, Array값 하나씩 y
- 누적값 x, Array값 하나씩 y
```java
public class ReduceDebugging {
    public static int debugArguments(int x, int y) {
        System.out.printf("x = %s, y = %s, sum = %s\n", x, y, x+y);
        return x+y;
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int sum = intList.stream().reduce(0, ReduceDebugging::debugArguments);
    }
}

/*
x = 0, y = 1, sum = 1
x = 1, y = 2, sum = 3
x = 3, y = 3, sum = 6
x = 6, y = 4, sum = 10
x = 10, y = 5, sum = 15
x = 15, y = 6, sum = 21
x = 21, y = 7, sum = 28
x = 28, y = 8, sum = 36
x = 36, y = 9, sum = 45
x = 45, y = 10, sum = 55

Process finished with exit code 0
*/
```

#### # 5.8.3 리듀스 연산 응용
- 리듀스 연산은 초기값과 누적값이라는 측면에서 가장 사용하기 좋다.
- 하지만 리듀스는 합계를 구하기 위해 만든 것이 아니며 람다 표현식을 어떻게 작성하느냐에 따라 여러 가지 결과값을 계산할 수 있다.
- 최소값, 최대값도 쉽게 구할 수 있다.
```java
public class ReduceMinMaxExample {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(4,2,8,1,9,6,7,3,5);
        
        int max = intList.stream().reduce(0, Integer::max);
        int min = intList.stream().reduce(0, Integer::min);
    }
}
```