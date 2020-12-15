package practical.chapter4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortCollection {

    public static void main(String[] args) {
        List<BaseballPlayer> list = new ArrayList<>();
        list.add(new BaseballPlayer("정재엽"));
        list.add(new BaseballPlayer("Alice"));
        list.add(new BaseballPlayer("박현정"));

        /*
        list.sort(new Comparator<BaseballPlayer>() {
            @Override
            public int compare(BaseballPlayer object1, BaseballPlayer object2) {
                return object1.getPlayerName().compareTo(object2.getPlayerName());
            }
        });
        */

        list.sort(
                (BaseballPlayer object1, BaseballPlayer object2)
                    -> object1.getPlayerName().compareTo(object2.getPlayerName())
        );

        for(BaseballPlayer baseballPlayer : list) {
            System.out.println(baseballPlayer.getPlayerName());
        }
    }
}
