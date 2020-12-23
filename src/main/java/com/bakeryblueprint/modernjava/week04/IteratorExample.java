package com.bakeryblueprint.modernjava.week04;

import java.util.Iterator;
import java.util.List;

public class IteratorExample {

  public static void main(final String[] args) {
    final List<HelloPerson> personList = HelloPerson.getSampleData();

    final Iterator<HelloPerson> peopleIterator = personList.iterator();

    while (peopleIterator.hasNext()) {
      final HelloPerson person = peopleIterator.next();
      System.out.println(person);
    }
  }
}
