package practical.chapter5;

import java.util.*;

public class StaticTest {
    public static void plusInteger(Integer i) {
        Set<Integer> set = new HashSet<>();
        set.add(i);

        System.out.println(set.size());
    }

    public void test() {
        plusInteger(1);
        plusInteger(1);
        plusInteger(2);
        plusInteger(2);
        plusInteger(3);
    }

    public static void main(String[] args) {
        new StaticTest().test();
    }
}
