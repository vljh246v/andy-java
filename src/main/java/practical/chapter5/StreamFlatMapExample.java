package practical.chapter5;

import java.util.Arrays;
import java.util.List;

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
               .flatMap((String[] array) -> Arrays.stream(array))
               .filter((String data) -> "a".equals(data.toString()))
               .forEach((String data) -> System.out.println(data));
    }
}
