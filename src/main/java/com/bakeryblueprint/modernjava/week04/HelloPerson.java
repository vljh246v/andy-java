package com.bakeryblueprint.modernjava.week04;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloPerson {

  private String firstName;
  private String lastName;
  private String country;

  public static List<HelloPerson> getSampleData() {
    final List<HelloPerson> person = new ArrayList<>();

    person.add(new HelloPerson("재엽", "정", "대한민국"));
    person.add(new HelloPerson("도연", "류", "미국"));
    person.add(new HelloPerson("현수", "김", "프랑스"));
    person.add(new HelloPerson("재현", "임", "일본"));
    person.add(new HelloPerson("승훈", "지", "중국"));

    return person;

  }

  @Override
  public String toString() {
    return this.getFirstName() + " " + this.getLastName() + " from " + this.getCountry();
  }
}
