package practical.chapter8;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattingDateTime {
    public static void main(String[] args) {
        try {
            // 현재 날짜와 시간을 구한다.
            LocalDateTime dateTime = LocalDateTime.now();
            System.out.printf("%s%n", dateTime.toString());

            // 사용자 정의된 포맷을 정의한다.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedDate = dateTime.format(formatter);
            System.out.printf("%s%n", formatedDate);
        }
        catch(DateTimeException e){
            e.printStackTrace();
        }
    }
}
