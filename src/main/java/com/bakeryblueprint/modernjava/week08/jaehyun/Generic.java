package com.bakeryblueprint.modernjava.week08.jaehyun;

public class Generic {

  public static void main(String[] args) {
    TestGenericObj<Long, Double, Integer> testGenericObj = new TestGenericObj();
    testGenericObj.init(1l);

    Class<?> aClass = ((Object) testGenericObj.parse(Long.valueOf(3930l))).getClass();

    System.out.println(aClass.getTypeName().equals(Double.class.getTypeName()));
    System.out.println(testGenericObj.parse(Long.valueOf(3930l)));
    System.out.println(testGenericObj.to(3.3));
  }

}
