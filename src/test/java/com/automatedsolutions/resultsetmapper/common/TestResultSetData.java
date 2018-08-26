package com.automatedsolutions.resultsetmapper.common;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/** @author Matthew Vass */
public class TestResultSetData {
  public static final String STRING_VALUE = "string value";

  public static final Integer INTEGER_VALUE = 39;

  public static final Boolean BOOLEAN_VALUE = true;

  public static final Double DOUBLE_VALUE = 150000.00;

  public static final Long LONG_VALUE = 12345678910L;

  public static final Short SHORT_VALUE = 123;

  public static final BigDecimal BIGDECIMAL_VALUE = BigDecimal.TEN;

  @SuppressWarnings("deprecation")
  public static final Time TIME_VALUE = new Time(10, 11, 12);

  public static final Timestamp TIMESTAMP_VALUE = new Timestamp(LONG_VALUE);

  public static final Date DATE_VALUE = new Date(LONG_VALUE);
}
