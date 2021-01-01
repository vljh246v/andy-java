package practical.chapter4.homework;

import java.util.ArrayList;
import java.util.List;

public class Homework6 {
    private String name;

    public Homework6(String name) {
        this.name = name;
    }

    public static void executeMethodExample(String name) {
        System.out.println((new Homework6(name)).toString());
    }

    @Override
    public String toString() {
        return "name : " + name;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("도연");
        list.add("현수");
        list.add("재엽");
        list.add("재현");

        //다음 람다 표현식을 생성자 참조, 메서드 참조를 사용하여 변형하시오
        System.out.println("Lambda Expression ~~~~~~~~");

        // 람다 표현식
        list.stream().map((String name) -> new Homework6(name))
                .forEach((Homework6 data) -> System.out.println(data));

        //생성자 참조
        list.stream().map(Homework6::new)
                .forEach((Homework6 data) -> System.out.println(data));

        //메서드 참조
        list.stream().forEach(Homework6::executeMethodExample);

    }
}