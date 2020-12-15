package practical.chapter4;

import java.util.function.UnaryOperator;

public class UnaryOperatorExample {
    public static void main(String[] args) {
        UnaryOperator<Integer> operatorA = (Integer t) -> t * 2;
        System.out.println(operatorA.apply(1));
        System.out.println(operatorA.apply(2));
        System.out.println(operatorA.apply(3));
    }
}
