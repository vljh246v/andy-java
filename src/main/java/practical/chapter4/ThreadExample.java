package practical.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ThreadExample {

    public static void main(String[] args) {
        /*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Thread!");
            }
        });
        */
        /*
        Thread thread = new Thread(() -> System.out.println("Hello Thread!"));
        thread.start();
        */

        //Runnable
        //4.3.3 람다 표현식과 변수
        int threadNumber = 100;
        List<String> list = new ArrayList<String>(threadNumber);
        list.add("정재엽");
        list.add("박현정");
        list.add("이우정");
        list.add("김다연");

        //Stream.class - void forEach(Consumer<? super T> var1);
        list.stream().forEach((String s) -> System.out.println(s + ", " + threadNumber));
    }
}
