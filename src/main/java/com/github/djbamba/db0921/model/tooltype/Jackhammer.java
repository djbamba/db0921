package com.github.djbamba.db0921.model.tooltype;

import com.github.djbamba.db0921.model.ToolType;
import java.math.BigDecimal;

public class Jackhammer implements ToolType {
  private final BigDecimal rate = new BigDecimal("2.99");

  @Override
  public String type() {
    return "Jackhammer";
  }

  @Override
  public BigDecimal dailyCharge() {
    return rate;
  }

  @Override
  public boolean weekendCharge() {
    return ToolType.super.weekendCharge();
  }

  @Override
  public boolean holidayCharge() {
    return ToolType.super.holidayCharge();
  }

  @Override
  public boolean weekdayCharge() {
    return true;
  }
}
