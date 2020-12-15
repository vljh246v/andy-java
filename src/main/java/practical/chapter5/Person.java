package practical.chapter5;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Person) {
            Person p = (Person)o;
            if(p.getName().equals(this.getName()) && p.getAge() == this.getAge()) return true;
            else return false;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return (name + age).hashCode();
    }

    @Override
    public String toString() {
        return "Name : " + name + ", Age : " + age + ", HashCode : " + this.hashCode();
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("정재엽", 10));
        personList.add(new Person("임재현", 11));
        personList.add(new Person("김현수", 12));
        personList.add(new Person("류도연", 13));
        personList.add(new Person("정재엽", 10));

        // # 이거 왜 안되는데 ?
        // distinct() - 내부적으로 Object.equals로 구현됨
        // public boolean equals(Object obj){this == obj};
        // hashCode / equals 함께 Override ㄱ
        personList.stream().distinct().forEach(System.out::println);
    }

    @Override
    public int compareTo(Person person) {
        return (name + age).compareTo(person.getName() + person.getAge());
    }
}
