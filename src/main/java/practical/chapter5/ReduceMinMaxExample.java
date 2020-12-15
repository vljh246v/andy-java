package practical.chapter5;

import java.util.Arrays;
import java.util.List;

public class ReduceMinMaxExample {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(4,2,8,1,9,6,7,3,5);

        int max = intList.stream().reduce(0, Integer::max);
        int min = intList.stream().reduce(0, Integer::min);
    }
}
