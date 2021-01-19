package practical.chapter8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class CustomDateFormat {
    // 사용자 날짜 패턴 정의
    public static final DateTimeFormatter KR_LOCAL_DATE;
    static {
        KR_LOCAL_DATE = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .toFormatter();
    }

    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.printf("날짜 : %s\n",
                currentDateTime.format(CustomDateFormat.KR_LOCAL_DATE));
    }
}
