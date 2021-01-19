package com.bakeryblueprint.modernjava.week06_jaehyun;

import java.time.LocalDate;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

public class Main {

  public static void main(final String[] args) {
    final TemporalQuery<TemporalUnit> chronoUnitQuery
        = TemporalQueries.precision();

    System.out.println("LocalDate 정밀도 : " + LocalDate.now().query(chronoUnitQuery));
  }

}
