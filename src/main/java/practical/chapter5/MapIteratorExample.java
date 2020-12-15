package practical.chapter5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

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
