package com.bakeryblueprint.modernjava.week08.jaehyun;

import java.util.ArrayList;
import java.util.List;

public class GenericErasureExample1 {

  public static void main(String[] args) {
    List<String> myList = new ArrayList<>();
    myList.add("Hello");
    myList.add("World");

    String hello = myList.get(0);
    String world = myList.get(1);

    System.out.println(hello + " " + world);
  }
}
