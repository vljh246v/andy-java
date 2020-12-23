package com.bakeryblueprint.modernjava.week03.jaehyun;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("### 1 ###");
        homework1();

        System.out.println("### 2 ###");
        homework2();
        homework3();
    }


    public static void homework1(){
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);

        List<List<String>> collect = numbers1.stream().map(integer -> {
            return numbers2.stream()
                    .map(integer1 -> "(" + integer + ", " + integer1 + ")")
                    .collect(Collectors.toList());
        }).collect(Collectors.toList());

        System.out.println(collect);
    }

    public static void homework2(){
        List<String> listStr = Arrays.asList("정","재","엽","ㅂ","ㅅ");
        Integer count = listStr.stream()
                .map(s -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);
    }

    public static void homework3(){
        Product computer = new Product("Samsung",1000000);
        Product keyboard = new Product("Reopold",200000);
        Product mouse = new Product("Logitech",5000);
        Product book = new Product("Book",15000);
        Product drink = new Product("CocaCola",1000);
        Product tv = new Product("LG",1100000);

        Order order1 = new Order(Arrays.asList(computer,keyboard,mouse),20201201);
        Order order2 = new Order(Arrays.asList(book,drink),20201221);
        Order order3 = new Order(Arrays.asList(tv),20191211);

        List<Order> orderList = Arrays.asList(order1, order2, order3);

        // 3-3. 2020년 이후 발생한 주문 + 주문일에 대한 오름차순 정렬 출력.
        System.out.println("### 3-3 ###");
        List<Order> collect = orderList.stream()
                .filter(order -> order.getOrderDate() >= 20200000)
                .sorted((o1, o2) -> o1.getOrderDate().compareTo(o2.getOrderDate()))
                .collect(Collectors.toList());

        System.out.println(collect);


        // 3-4. 2020년 이후 발생한 주문 총액.
        System.out.println("### 3-4 ###");
        Integer sum = orderList.stream()
                .filter(order -> order.getOrderDate() >= 20200000)
                .map(Order::getOrderAmt)
                .reduce(0, Integer::sum);

        System.out.println(sum);


        // 3-5. 주문 총액이 1000000원 이상의 주문정보 출력.
        System.out.println("### 3-5 ###");
        orderList.stream()
                .filter(order -> order.getOrderAmt() >= 1000000)
                .forEach(System.out::println);


        // 3-6. 모든 주문 "단품" 가격 오름차순 정렬 출력.
        System.out.println("### 3-6 ###");
        List<Product> result = orderList.stream().map(Order::getProductList)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Product::getPrice, Integer::compareTo))
                .collect(Collectors.toList());

        System.out.println(result);

    }
}
