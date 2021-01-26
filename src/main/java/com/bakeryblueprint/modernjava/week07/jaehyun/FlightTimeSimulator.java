package com.bakeryblueprint.modernjava.week07.jaehyun;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FlightTimeSimulator {
//12 10 7
  public static void main(String[] args) {
    DateTimeFormatter format =
        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

    LocalDateTime baseTime = LocalDateTime.of(2021, Month.JANUARY, 10, 10, 00);

    City seoul = new City("Seoul", ZoneId.of("Asia/Seoul"));
    City losAngeles = new City("Los_Angeles", ZoneId.of("America/Los_Angeles"));
    City roma = new City("roma", ZoneId.of("Europe/Rome"));
    City paris = new City("paris", ZoneId.of("Europe/Paris"));
    City tokyo = new City("tokyo", ZoneId.of("Asia/Tokyo"));

    TimeTable timeTable = new TimeTable(baseTime);
    timeTable.printTimeTable(seoul, losAngeles, 720l);
    timeTable.printTimeTable(roma, seoul, 600l);
    timeTable.printTimeTable(paris, tokyo, 420l);
  }
}
