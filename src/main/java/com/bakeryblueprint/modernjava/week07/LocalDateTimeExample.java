package practical.chapter8;

import java.time.LocalDateTime;
import java.time.Month;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        // 현재 로컬 시간 생성
        LocalDateTime localDateTime = LocalDateTime.now();

        // 지정 로컬 시간 생성
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, Month.APRIL, 1, 16, 30);

        // 날짜 이후, 이전
        System.out.printf("1달 후 : %s%n", localDateTime2.plusMonths(1));
        System.out.printf("1달 후 : %s%n", localDateTime2.minusMonths(1));
    }
}
