package practical.chapter8;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DatePeriod {
    public static void main(String[] args) {
        // 노무현 남북정상회담
        LocalDate oldDate = LocalDate.of(2007,10,3);

        // 문재인 남북정상회담
        LocalDate newDate = LocalDate.of(2018,4,27);

        // 정상회담 시간 차이 계산
        Period period = Period.between(oldDate, newDate);

        // 차잇값 출력
        System.out.printf("%s년 %s개월 %s일만에 정상회담 개최",
                period.getYears(), period.getMonths(), period.getDays());

        // 일자 차잇값 출력
        long days = ChronoUnit.DAYS.between(oldDate, newDate);
        System.out.println(days + "일만에 정상회담 개최");
    }
}
