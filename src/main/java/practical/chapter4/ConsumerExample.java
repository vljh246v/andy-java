package practical.chapter4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void executeConsumer(List<String> nameList, Consumer<String> consumer) {
        for(String name : nameList) {
            consumer.accept(name);
        }
    }

    // Consumer 인터페이스 활용 예제
    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("정재엽");
        nameList.add("박현정");
        nameList.add("이우정");
        nameList.add("김다연");

        ConsumerExample.executeConsumer(nameList,
                (String name) -> System.out.println(name));
    }
}
