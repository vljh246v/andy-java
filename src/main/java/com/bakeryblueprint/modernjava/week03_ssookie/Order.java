package com.bakeryblueprint.modernjava.week03_ssookie;

import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private final List<Product> productList;
    private final Integer orderDate;
    private Integer orderAmt;

    public Order(List<Product> productList, Integer orderDate) {
        this.productList = productList;
        this.orderDate = orderDate;
        // 3-1. 주문상품의 합을 이용해 주문 총 금액 세팅.
        this.orderAmt = productList.stream()
                .map(i -> i.getPrice())
                .reduce(0, Integer::sum);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Integer getOrderDate() {
        return orderDate;
    }

    public Integer getOrderAmt() {
        return orderAmt;
    }

    public String toString() {
        // 3-2. result 값 = 상품리스트의 이름을 ^로 붙이기 Ex) 상품명^상품명^상품명
        String result = productList.stream()
                .map(i -> i.getProductName())   // map(Product::getProductName)
                .collect(Collectors.joining("^"));
        return result + ", " + this.orderDate + ", " + this.orderAmt;
    }
}
