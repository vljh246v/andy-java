package com.bakeryblueprint.modernjava.week08.jaehyun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericExample1 {

  public static void main(String[] args) {
    List myList = new ArrayList();
    List<String> stringList = new ArrayList();
    List<Integer> integerList = new ArrayList();

    System.out.println(myList instanceof ArrayList);
//    System.out.println(stringList instanceof ArrayList<String>);
//    System.out.println(integerList instanceof ArrayList<Integer>);

    Map<String, String> prop = new HashMap<>();
    Map<String, String> sortedProp = Utils.sorting(prop);
  }
}
