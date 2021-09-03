package com.github.djbamba.db0921.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Class to handle date-related logic */
public class DateUtil {
  private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
  private static final DateTimeFormatter M_D_YY = DateTimeFormatter.ofPattern("M/d/yy");

  private DateUtil() {}

  public static LocalDateTime dateFromString(String dateString) {
    LocalDate localDate = LocalDate.parse(dateString, M_D_YY);
    LocalDateTime dateTime = LocalDateTime.of(localDate, LocalDateTime.MIN.toLocalTime());
    log.debug("dateFromString {} => {}", dateString, dateTime);

    return dateTime;
  }

  public static String dateStringFormatted(LocalDateTime date) {
    String dateString = M_D_YY.format(date);
    log.debug("dateStringFormatted {} => {}", date, dateString);

    return dateString;
  }

  public static boolean isWeekday(LocalDateTime date) {
    switch (date.getDayOfWeek()) {
      case SATURDAY:
      case SUNDAY:
        return false;
      default:
        return true;
    }
  }

  public static boolean isWeekend(LocalDateTime dateTime) {
    return !isWeekday(dateTime);
  }

  /**
   * Checks if supplied date is an observed holiday. Holidays: Independence Day, Labor Day.
   *
   * @param date date to check
   * @return true if observed
   */
  public static boolean isHoliday(LocalDateTime date) {
    boolean isIndependenceDay = Month.JULY.equals(date.getMonth()) && 4 == date.getDayOfMonth();
    boolean isLaborDay =
        Month.SEPTEMBER.equals(date.getMonth())
            && DayOfWeek.MONDAY.equals(date.getDayOfWeek())
            && date.getDayOfMonth() <= 7;

    return isIndependenceDay || isLaborDay;
  }
}
