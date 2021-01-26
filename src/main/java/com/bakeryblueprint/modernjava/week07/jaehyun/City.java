package com.bakeryblueprint.modernjava.week07.jaehyun;

import java.time.ZoneId;

public class City {

  public City(String cityName, ZoneId zoneId) {
    this.cityName = cityName;
    this.zoneId = zoneId;
  }

  private String cityName;
  private ZoneId zoneId;


  public ZoneId getZoneId() {
    return zoneId;
  }

  public String getCityName() {
    return cityName;
  }
}
