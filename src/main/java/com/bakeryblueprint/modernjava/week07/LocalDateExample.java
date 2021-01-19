package practical.chapter8;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateExample {
    public static void main(String[] args) {

        // 현재 날짜 객체를 생성
        LocalDate today = LocalDate.now();

        // 과거 날짜 객체를 생성
        LocalDate birthday = LocalDate.of(1992, Month.APRIL, 12);

        // 과거 날짜 객체 정보를 수정
        LocalDate nextBDay = birthday.withYear(today.getYear());

        System.out.println(nextBDay);

        // 날짜 객체 비교, 생일이 지났는지 여부를 판단
        if(nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }

        System.out.println(nextBDay);
    }
}
