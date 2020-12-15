package practical.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OldPrintList {
    //for each 구문으로 출력
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("정재엽");
        list.add("박현정");
        list.add("이우정");
        list.add("김다연");

        // for each 문장을 이용한 데이터 출력
        /*
        for(String entity : list) {
            System.out.println(entity);
        }
        */
        list.stream().forEach((String name) -> System.out.println(name));
        /*
        list.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        */
        list.stream().forEach(System.out::println);
    }
}
