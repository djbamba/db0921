package com.github.djbamba.db0921.model;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Tool implements Rentable {
  private final ToolType toolType;

  @Getter(AccessLevel.NONE)
  private final Brand brand;

  private final String toolCode;

  @Override
  public String getCode() {
    return getToolCode();
  }

  @Override
  public String getBrand() {
    return brand.name();
  }

  @Override
  public String getName() {
    return getToolType().name();
  }

  @Override
  public BigDecimal getDailyCharge() {
    return getToolType().getDailyCharge();
  }

  @Override
  public boolean isWeekdayCharge() {
    return getToolType().isWeekdayCharge();
  }

  @Override
  public boolean isWeekendCharge() {
    return getToolType().isWeekendCharge();
  }

  @Override
  public boolean isHolidayCharge() {
    return getToolType().isHolidayCharge();
  }
}
