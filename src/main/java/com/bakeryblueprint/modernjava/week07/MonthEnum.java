package practical.chapter8;

import java.time.Month;
import java.util.Calendar;

public class MonthEnum {
    public static void main(String[] args) {
        System.out.println("Month.FEBRUARY의 값은 " + Month.FEBRUARY);

        // 숫자 2에 해당하는 월 객체를 생성
        Month february = Month.of(2);
        System.out.println("Month.of(2)의 값은 " + february);

        // 비교
        System.out.println("비교 " + february.equals(Month.FEBRUARY));

        //Calendar.FEBRUARY
    }
}
