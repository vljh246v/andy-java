package com.bakeryblueprint.modernjava.week02_ssookie;

import java.util.function.Supplier;

/*
 * 5. "lamdaTest" 문자열을 반환 받는 람다식을 작성하시오(Supplier interface 사용)
 */
public class SupplierExample {
    // parameter 없이 return type만 존재함
	public static String executeSupplier(Supplier<String> supplier) {
		return supplier.get();
	}

	public static void main(String[] args) {
		String param = "lambdaTest";
		SupplierExample.executeSupplier(() -> {return param;});
	}
}