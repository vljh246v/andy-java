package practical.chapter3;

import java.util.ArrayList;
import java.util.List;

/*
예) 주인이 김현수인것 / 색상이 red인것 /무게 10 이상인 것 추출 람다 표현식, 함수평 프로그래밍이 왜 필요한지 흐름을 이해하기 위함이다.
2-1) 인터페이스와 익명 클래스를 사용
2-2) 람다 표현식 사용
2-3) 메서드 참조 기능 사용
* */
public class Apple {
    private String owner;
    private String color;
    private int weight;
    private List<Apple> appleList = new ArrayList<>();

    public Apple() {
        initializeApple();
    }

    private Apple(String owner, String color, int weight) {
        this.owner = owner;
        this.color = color;
        this.weight = weight;
    };

    private void initializeApple() {
        appleList.add(new Apple("정재엽","green",5));
        appleList.add(new Apple("임재현","green",7));
        appleList.add(new Apple("김현수","red",110));
        appleList.add(new Apple("류도연","red",120));
    }

    public String getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // 함수형 인터페이스
    public List<Apple> searchApple(AppleFilter appleCondition) {
        List<Apple> returnValue = new ArrayList<>();

        for(Apple apple : appleList) {
            if(appleCondition.isMatched(apple)) {
                returnValue.add(apple);
            }
        }
        return returnValue;
    }

   // 메서드 참조
    public static boolean isSatisfiedCondition(Apple apple) {
        if(apple.getOwner().equals("김현수")
                && apple.getColor().equals("red")
                && apple.getWeight() >= 10) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(owner).append(" : ").append(color).append(" : ").append(weight);
        return builder.toString();
    }

    public static void main(String[] args) {
        Apple apple = new Apple();

        List<Apple> searchApple =
                apple.searchApple(new AppleFilter() {
                    @Override
                    public boolean isMatched(Apple apple) {
                        if(apple.getOwner().equals("김현수")
                        && apple.getColor().equals("red")
                        && apple.getWeight() >= 10) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                });

        List<Apple> searchAppleLambda =
                apple.searchApple((Apple a) -> a.getOwner().equals("김현수"));

        List<Apple> searchAppleLambdaSecond =
                apple.searchApple((Apple a) -> {
                    return a.getOwner().equals("김현수") && a.getColor().equals("red");
                });

        List<Apple> searchAppleMethod =
                apple.searchApple(Apple::isSatisfiedCondition);

        for(Apple a : searchApple) {
            System.out.println(a.toString());
        }

        for(Apple a : searchAppleLambda) {
            System.out.println(a.toString());
        }

        for(Apple a : searchAppleLambdaSecond) {
            System.out.println(a.toString());
        }

        for(Apple a : searchAppleMethod) {
            System.out.println(a.toString());
        }
    }
}