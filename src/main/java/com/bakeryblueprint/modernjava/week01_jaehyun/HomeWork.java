package com.bakeryblueprint.modernjava.week01_jaehyun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeWork {
    public static void main(String[] args) {
        Apple apple1 = new Apple();
        apple1.setColor("red");
        apple1.setOwner("김현수");
        apple1.setWeight(11);


        Apple apple2 = new Apple();
        apple2.setColor("black");
        apple2.setOwner("정재엽");
        apple2.setWeight(10);

        Apple apple3 = new Apple();
        apple3.setColor("blue");
        apple3.setOwner("류도연");
        apple3.setWeight(9);

        List<Apple> apples = Arrays.asList(apple1, apple2, apple3);

        // 익명 클래스
        List<Apple> anonymousClassResult = searchApple(apples, new AppleFilter() {
            @Override
            public boolean isMatched(Apple apple) {
                return "김현수".equals(apple.getOwner())
                        && "red".equals(apple.getColor())
                        && apple.getWeight() > 10;
            }
        });

        System.out.println(anonymousClassResult);


        // 인터페이스 클래스
        List<Apple> interfaceResult = searchApple(apples, new InterfaceAppleFilter());
        System.out.println(interfaceResult);

        // 람다 클래스
        List<Apple> lambdaResult = searchApple(apples, apple -> {
            return "김현수".equals(apple.getOwner())
                    && "red".equals(apple.getColor())
                    && apple.getWeight() > 10;
        });
        System.out.println(lambdaResult);


        // 메소드 참조
        List<Apple> methodReferenceResult = searchApple(apples, new InterfaceAppleFilter()::isMatched);
        System.out.println(methodReferenceResult);
    }

    public static List<Apple> searchApple(List<Apple> list, AppleFilter appleFilter){
        List<Apple> result = new ArrayList<>();

        for(Apple apple : list){
            if(appleFilter.isMatched(apple))
                result.add(apple);
        }
        return result;
    }
}
