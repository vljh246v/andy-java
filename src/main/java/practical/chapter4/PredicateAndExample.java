package practical.chapter4;

import java.util.function.Predicate;

public class PredicateAndExample {
    //남자인지 판단
    public static Predicate<Person> isMale() {
        return (Person p) -> "male".equals(p.getSex());
    }
    // 성인인지 판단
    public static Predicate<Person> isAdult() {
        return (Person p) -> p.getAge() > 20;
    }

    public static void main(String[] args) {
        Predicate<Person> predicateA = PredicateAndExample.isMale();
        Predicate<Person> predicateB = PredicateAndExample.isAdult();
        Predicate<Person> predicateAB = predicateA.and(predicateB);

        Person person = new Person();
        person.setName("정재엽");
        person.setAge(35);
        person.setSex("male");

        System.out.println(person.getName() + "'s result : " +
                predicateAB.test(person));



    }
}
