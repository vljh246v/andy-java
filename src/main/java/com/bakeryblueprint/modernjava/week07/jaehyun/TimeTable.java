package com.bakeryblueprint.modernjava.week07.jaehyun;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTable {

  private Long flyTime;
  private City departure;
  private City arrival;
  private LocalDateTime baseTime;
  private DateTimeFormatter format;

  public TimeTable(LocalDateTime baseTime){
    this(baseTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
  }

  public TimeTable(LocalDateTime baseTime, DateTimeFormatter format){
    this.baseTime = baseTime;
    this.format = format;
  }
  public void printTimeTable(City departureCity, City arrivalCity, Long flyTime){

    System.out.println(departureCity.getCityName() + " to " + arrivalCity.getCityName());
    ZonedDateTime departure = ZonedDateTime.of(baseTime, departureCity.getZoneId());
    try {
      String out1 = departure.format(format);
      System.out.println("출발 : " + out1 + ", " + departureCity.getZoneId());
    }catch (Exception e){
      e.printStackTrace();
    }

    ZonedDateTime arrival = departure.withZoneSameInstant(arrivalCity.getZoneId()).plusMinutes(flyTime);
    try {
      String out2 = arrival.format(format);
      System.out.println("도착 : " + out2 + ", " + arrivalCity.getZoneId());
    }catch (Exception e){
      e.printStackTrace();
    }

    if(arrivalCity.getZoneId().getRules().isDaylightSavings(arrival.toInstant())){
      System.out.println("서머타임 시간 적용!" + arrivalCity.getZoneId());
    }else {
      System.out.println("표준 시간 적용!" + arrivalCity.getZoneId());
    }
  }
}
