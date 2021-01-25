package com.bakeryblueprint.modernjava.week08.jaehyun;

import java.util.ArrayList;
import java.util.List;

public class GenericBoundExample2 {


  public <T extends Number> void test1(List<T> list){
    for(Number n : list) {
      System.out.println(n);
    }
  }

  public void test2(List<? extends Number> list){
    for(Number n : list) {
      System.out.println(n);
    }
  }

  public static void main(String[] args) {
    List<Integer> ee = new ArrayList<>();
    ee.add(1);
    ee.add(2);
    ee.add(3);
    GenericBoundExample2 genericBoundExample = new GenericBoundExample2();
    genericBoundExample.test1(ee);
    genericBoundExample.test2(ee);
  }
}
