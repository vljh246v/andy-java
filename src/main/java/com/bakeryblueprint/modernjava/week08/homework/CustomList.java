package com.bakeryblueprint.modernjava.week08.homework;

import java.util.ArrayList;
import java.util.List;

public class CustomList<T> {
  private List<T> list = new ArrayList<>();
  public void add(T element){
    list.add(element);
  }
  public T get(int index) {
    return list.get(index);
  }

  public static void main(String[] args) {
    CustomList<String> list = new CustomList();

    list.add("ee");
    System.out.println(list.get(0));
  }
}