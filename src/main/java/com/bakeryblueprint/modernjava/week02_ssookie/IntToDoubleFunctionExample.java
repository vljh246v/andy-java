package com.bakeryblueprint.modernjava.week02_ssookie;

import java.util.function.Function;
import java.util.function.IntToDoubleFunction;

/*
 * 4. int형 변수인 x를 입력 받아 double형으로 리턴하는 람다 표현식을 작성하시오 (IntToDoubleFunction 사용)
 * ===> IntToDoubleFunction 사용 못함 ㅠ
 */
public class IntToDoubleFunctionExample {
	public static Double executeFunction(int param, Function<Integer, Double> function) {
		return function.apply(param);
	}
	public static void main(String[] args) {
        IntToDoubleFunctionExample.executeFunction(3, (Integer param) -> (double)param);
    }
}
