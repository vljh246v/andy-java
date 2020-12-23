# 2020.12.23

## Stream 문제

- Question1. 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하라.
  Ex) 두개의 리스트[1,2,3]과 [3,4]가 주어지면 [(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
```java
List<Integer> numbers1 = Arrays.asList(1,2,3);
List<Integer> numbers2 = Arrays.asList(3,4); 
```

- Question2. map과 reduce 메서드를 이용해서 스트림의 갯수를 계산하라.<br>
  참고로 count() 사용 금지.
```java
List<String> listStr = Arrays.asList("정","재","엽","천","재");
```

- Question3. Order / Product 예제<br>
  주석으로 문제가 있음.
```java
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
```
```java
public class Order {
    private final List<Product> productList;
    private final Integer orderDate;
    private Integer orderAmt;

    public Order(List<Product> productList, Integer orderDate) {
        this.productList = productList;
        this.orderDate = orderDate;
        // 3-1. 주문상품의 합을 이용해 주문 총 금액 세팅.
        this.orderAmt = ?;
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
        String result = ?;
        return result + ", " + this.orderDate + ", " + this.orderAmt;
    }
}
```
```java
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

        // 3-4. 2020년 이후 발생한 주문 총액.

        // 3-5. 주문 총액이 1000000원 이상의 주문정보 출력.

        // 3-6. 모든 주문 "단품" 가격 오름차순 정렬 출력.

    }
}
```