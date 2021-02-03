package practical.chapter11;

import java.util.Optional;
import java.util.OptionalInt;

public class FlatMapExample {
    private String name;
    private Optional<String> age;

    public FlatMapExample(String name, String age) {
        this.name = name;
        this.age = Optional.ofNullable(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getAge() {
        return age;
    }

    public void setAge(Optional<String> age) {
        this.age = age;
    }

    public static void main(String[] args) {
        FlatMapExample example = new FlatMapExample("JY Jeong", "30");
        Optional<FlatMapExample> optExample = Optional.of(example);

        /*
        public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
            Objects.requireNonNull(mapper);
            return !this.isPresent() ? empty() : ofNullable(mapper.apply(this.value));
        }
        */
        // map 이용
        Optional<Optional<String>> age = optExample.map(entity -> entity.getAge());
        System.out.printf("map 결과 : %s\n", age.get().get());

        /*
        public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) {
            Objects.requireNonNull(mapper);
            if (!this.isPresent()) {
                return empty();
            } else {
                Optional<U> r = (Optional)mapper.apply(this.value);
                return (Optional)Objects.requireNonNull(r);
            }
        }
        */
        // flatMap 이용
        Optional<String> flatAge = optExample.flatMap(entity -> entity.getAge());
        System.out.printf("flatMap 결과 : %s\n", flatAge.get());
    }
}
