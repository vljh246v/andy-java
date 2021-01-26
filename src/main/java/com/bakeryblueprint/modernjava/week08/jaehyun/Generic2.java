package com.bakeryblueprint.modernjava.week08.jaehyun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generic2 {

  static class A{};
  static class B extends A{};

  public static void main(String[] args) {
    List<B> listB1 = new ArrayList<>();
    List<? extends A> listA1 = listB1;
    List<? super  B> listB2 = listB1;
    listA1.add(null); // ?


    List<Integer> list = Arrays.asList(1, 2, 3, 4, 4, 5, 3);
    System.out.println(frequency(list, 1));
  }

  public <T> void method1(List<T> list) {

  }

  public void method2(List<?> list) {
    list.add(null);
  }

  public static  long frequency(List<? extends Object> list, Object element){
    return list.stream()
        .filter(it -> it.equals(element))
        .count();
  }



}
