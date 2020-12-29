package com.bakeryblueprint.modernjava.week03.jaehyun;

public class Product {
    //상품코드는 생략함.
    private final String productName;
    private final Integer price;

    public Product(String productName, Integer price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getPrice() {
        return price;
    }

    public String toString() {
        return "ProductName : " + this.productName + ", Price : " + this.price;
    }
}