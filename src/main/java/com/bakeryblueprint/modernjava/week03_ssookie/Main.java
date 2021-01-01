package com.bakeryblueprint.modernjava.week03_ssookie;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
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
        System.out.println("=== 3-3. 2020년 이후 발생한 주문 + 주문일에 대한 오름차순 정렬 출력. ===");
        List<Order> result3 = orderList.stream()
                .filter(i -> i.getOrderDate() > 20200000)
                .sorted((o1, o2) -> o1.getOrderDate().compareTo(o2.getOrderDate()))
                .collect(Collectors.toList());
        result3.forEach(System.out::println);

        // 3-4. 2020년 이후 발생한 주문 총액.
        System.out.println("=== 3-4. 2020년 이후 발생한 주문 총액. ===");
        int result4 = orderList.stream()
                .filter(i -> i.getOrderDate() >= 20200000)
                .map(Order::getOrderAmt)
                .reduce(0, Integer::sum);
        System.out.println(result4);

        // 3-5. 주문 총액이 1000000원 이상의 주문정보 출력.
        System.out.println("=== 3-5. 주문 총액이 1000000원 이상의 주문정보 출력. ===");
        orderList.stream()
                .filter(i -> i.getOrderAmt() >= 1000000)
                .forEach(System.out::println);

        // 3-6. 모든 주문 "단품" 가격 오름차순 정렬 출력.
        System.out.println("=== 3-6. 모든 주문 \"단품\" 가격 오름차순 정렬 출력. ===");
        List<Product> result6 = orderList.stream()
                .map(Order::getProductList)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Product::getPrice, Integer::compareTo))
                .collect(Collectors.toList());
        result6.forEach(System.out::println);
    }
}
