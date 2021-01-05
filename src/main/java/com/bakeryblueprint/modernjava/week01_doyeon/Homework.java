package com.bakeryblueprint.modernjava.week01_doyeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Homework {

    public static void main(String[] args) {
        SpringApplication.run(Homework.class, args);

        List<Apple> list = new ArrayList<Apple>();

        Apple apple = new Apple("도연", "red", 100);
        list.add(apple);

        Apple apple2 = new Apple("류도연", "black", 1001);
        list.add(apple2);

        //익명함수
        List<Apple> anymousMethod = searchAppleInfo(list, new AppleFilter() {
            @Override
            public boolean isMached(Apple apple) {
                return "도연".equals(apple.getOwner());
            }
        });

        // 람다
        List<Apple> lamda = searchAppleInfo(list, (apple1 -> {
            return "도연".equals(apple1.getOwner());
        }));

        // 메서드 참조
        List<Apple> methodReference = searchAppleInfo(list, new AppleFilterInterface()::isMached);
    }

    public static List<Apple> searchAppleInfo(List<Apple> list, AppleFilter appleFilter) {
        List<Apple> returnValue = new ArrayList<>();

        for(Apple apple : list) {
            // 인터페이스의 isMatched 호출 (실제 구현에 대해서는 캡슐화 되어 있음)
            if (appleFilter.isMached(apple)) {
                returnValue.add(apple);
            }
        }
        return returnValue;
    }
}


