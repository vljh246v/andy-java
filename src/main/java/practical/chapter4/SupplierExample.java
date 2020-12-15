package practical.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SupplierExample {
    public static String executeSupplier(Supplier<String> supplier) {
        return supplier.get();
    }

    // Supplier 인터페이스 실행
    public static void main(String[] args) {
        String version = "Java upgrade book, version 0.1 BETA";
        SupplierExample.executeSupplier(() -> {return version;});

        Supplier<BaseballPlayer> s3 = () -> new BaseballPlayer("정재엽");
        System.out.println(s3.get().getPlayerName());

        Supplier<List<BaseballPlayer>> s4 = () -> {
            List<BaseballPlayer> list = new ArrayList<BaseballPlayer>();
            list.add(new BaseballPlayer("정재엽"));
            list.add(new BaseballPlayer("박현정"));
            list.add(new BaseballPlayer("이우정"));
            list.add(new BaseballPlayer("김다연"));
            return list;
        };

        for(BaseballPlayer baseballPlayer : s4.get()) {
            System.out.println(baseballPlayer.getPlayerName());
        }

    }
}
