package practical.chapter5;

import java.util.*;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public class Memo {
    public static void main(String[] args) {
        Integer[] intArray = new Integer[] {1,2,3,4,5,6,7};
        List numberList = Arrays.asList(intArray);

        Iterator iter = numberList.iterator();

        Stream stream = numberList.stream();
        stream.close();
        stream.parallel();

        //Stream
    }
}
