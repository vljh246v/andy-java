package practical.chapter8;

import java.time.*;
import java.time.temporal.Temporal;

public class LocalDateTimeAdjusters {
    public static void main(String[] args) {
        // 1. 동일형의 변환
        LocalDateTime beforeDate = LocalDateTime.of(2018, Month.APRIL, 10, 8 , 40);
        LocalDateTime afterDate = LocalDateTime.now();

        // afterDate를 beforeDate 값으로 변환해서 리턴한다.
        Temporal temporalDate = beforeDate.adjustInto(afterDate);

        System.out.printf("Before Date : %s%n", beforeDate);
        System.out.printf("After Date : %s%n", afterDate);
        System.out.printf("Temporal Date : %s%n", temporalDate);

        // 2. 다른 형의 변환
        LocalDate localDate = LocalDate.of(2018, Month.APRIL, 10);
        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Los_Angeles"));
        Temporal temporalObject = localDate.adjustInto(zdt);

        System.out.println(zdt);
        System.out.println(temporalObject);

        if(temporalObject instanceof LocalDate) {
            System.out.println("LocalDate");
        }

        if(temporalObject instanceof ZonedDateTime) {
            System.out.println("ZonedDateTime");
        }
    }
}
