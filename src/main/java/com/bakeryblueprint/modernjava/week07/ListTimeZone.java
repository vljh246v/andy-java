package practical.chapter8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTimeZone {
    public static void main(String[] args) {

        // 모든 타임존 목록을 조회해서 ArrayList 컬렉션에 담는다.
        List<String> timeZoneList = new ArrayList<String>(ZoneId.getAvailableZoneIds());
        Collections.sort(timeZoneList);

        // 현지 로컬 날짜 시간을 구한다.
        LocalDateTime localDateTime = LocalDateTime.now();

        // 타임존 기반의 시간 차이를 구한다.
        for (String timeZoneId : timeZoneList) {
            ZoneId zone = ZoneId.of(timeZoneId);

            // 타임존 기반의 날짜 시간 객체 생성
            ZonedDateTime zdt = localDateTime.atZone(zone);

            // 타임존 기반의 시차 객체 생성
            ZoneOffset offset = zdt.getOffset();

            String out = String.format("%25s %8s%n", zone, offset);

            // 초 단위 시차를 시간 단위 시차로 변경
            int secondsOfHour = offset.getTotalSeconds();
            //System.out.printf(out);
            // 시차가 존재할 경우 출력한다. 시차가 없을 경우 출력x
            if(secondsOfHour != 0) {
               System.out.printf(out);
            }
        }
    }
}
