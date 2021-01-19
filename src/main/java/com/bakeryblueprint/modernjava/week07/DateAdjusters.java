package practical.chapter8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateAdjusters {
    public static void main(String[] args) {

        // 현재 일자를 구한다.
        LocalDate localDate = LocalDate.now();

        // 요일 객체를 가져온다.
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.printf("%s 일의 요일은 %s 입니다.%n", localDate, dayOfWeek);
        /*
        public LocalDate with(TemporalAdjuster adjuster) {
            return adjuster instanceof LocalDate ? (LocalDate)adjuster : (LocalDate)adjuster.adjustInto(this);
        }
        * */
        System.out.printf("이번 달의 첫 번째 일 : %s%n",
                localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.printf("이번 달의 첫 번째 화요일 : %s%n",
                localDate.with(TemporalAdjusters.firstInMonth(dayOfWeek)));
        System.out.printf("이번 달의 마지막 일 : %s%n",
                localDate.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.printf("다음 달의 첫 번째 일 : %s%n",
                localDate.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.printf("내년 첫 번째 일 : %s%n",
                localDate.with(TemporalAdjusters.firstDayOfNextYear()));

    }
}
