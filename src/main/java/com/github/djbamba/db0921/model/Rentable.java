package com.github.djbamba.db0921.model;

import java.math.BigDecimal;

/** Represents a rentable product. */
public interface Rentable {

  /** Unique ID for rental */
  String getCode();

  /** Name of rental */
  String getName();

  /** Brand of rental */
  String getBrand();

  /** The daily rental charge */
  BigDecimal getDailyCharge();

  /** If daily charge applicable during weekdays */
  boolean isWeekdayCharge();

  /** If daily charge applicable during weekends */
  boolean isWeekendCharge();

  /** If daily charge applicable during holidays */
  boolean isHolidayCharge();
}
