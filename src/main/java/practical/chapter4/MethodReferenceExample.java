package practical.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MethodReferenceExample {
    public static MethodReferenceExample of() {
        return new MethodReferenceExample();
    }

    // Data 처리 Logic.
    public static void executeMethod(String entity) {
        if(entity != null && !"".equals(entity)) {
            System.out.println("Contents : " + entity);
            System.out.println("Length : " + entity.length());
        }
    }

    // 대문자로 변경하는 코드
    public void toUpperCase(String entity) {
        System.out.println(entity.toUpperCase());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // 정적 메서드 참조
        list.stream().forEach(MethodReferenceExample::executeMethod);

        // 한정적 메서드 참조
        list.stream().forEach(MethodReferenceExample.of()::toUpperCase);

        // 비한정적 메서드 참조
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        /*
        list.stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        */

        list.stream().forEach(System.out::println);
    }
}
