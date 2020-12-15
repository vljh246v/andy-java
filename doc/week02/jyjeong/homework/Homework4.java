package practical.chapter4.homework;

import java.util.function.IntToDoubleFunction;

public class Homework4 {
    public static void main(String[] args) {
        //int형 변수인 x를 입력 받아 double형으로 리턴하는 람다 표현식을 작성하시오 (IntToDoubleFunction 사용)
        IntToDoubleFunction intToDoubleFunction = i -> i/2;
        System.out.println(intToDoubleFunction.applyAsDouble(1));
    }
}
