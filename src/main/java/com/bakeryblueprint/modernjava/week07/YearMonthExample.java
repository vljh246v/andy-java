package practical.chapter8;

import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;

public class YearMonthExample {
    public static void main(String[] args) {
        // 현재 기준으로 생성
        YearMonth date = YearMonth.now();
        System.out.println(date + " : " + date.lengthOfMonth());

        // 1년 추가
        YearMonth date2 = date.plusYears(1);
        System.out.println(date2 + " : " + date2.lengthOfMonth());

        // 1개월 추가
        YearMonth date3 = date.plusMonths(1);
        System.out.println(date3 + " : " + date3.lengthOfMonth());

        // MonthDay 사용 예
        MonthDay date4 = MonthDay.of(Month.JANUARY, 12);
        boolean validYear = date4.isValidYear(2021);
        System.out.println(validYear);
    }
}
