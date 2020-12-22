package com.bakeryblueprint.modernjava.week04;

import java.util.List;
import java.util.Spliterator;

public class SpliteratorExample {

  public static void printSpliterator(final Spliterator<HelloPerson> spliterator) {
    spliterator.forEachRemaining(it -> {
      System.out.println(it);
    });
  }

  public static void main(final String[] args) {
    final List<HelloPerson> personList = HelloPerson.getSampleData();

    final Spliterator<HelloPerson> spliterator1 = personList.spliterator();
    // 분할
    SpliteratorExample.printSize("spliterator1", spliterator1);

    final Spliterator<HelloPerson> spliterator2 = spliterator1.trySplit();
    System.out.println("첫 번째 split 후");
    SpliteratorExample.printSize("spliterator1", spliterator1);
    SpliteratorExample.printSize("spliterator2", spliterator2);

    final Spliterator<HelloPerson> spliterator3 = spliterator1.trySplit();
    System.out.println("두 번째 split 후");
    SpliteratorExample.printSize("spliterator1", spliterator1);
    SpliteratorExample.printSize("spliterator2", spliterator2);
    SpliteratorExample.printSize("spliterator3", spliterator3);

    System.out.println(" ");
    System.out.println("spliterator1 출력");
    SpliteratorExample.printSpliterator(spliterator1);
    System.out.println("spliterator2 출력");
    SpliteratorExample.printSpliterator(spliterator2);
    System.out.println("spliterator3 출력");
    SpliteratorExample.printSpliterator(spliterator3);

  }

  public static void printSize(final String name, final Spliterator<HelloPerson> spliterator) {
    System.out.println("Estimated size (" + name + ") : " + spliterator.estimateSize());
  }
}
