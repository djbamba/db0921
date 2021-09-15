package com.github.djbamba.db0921.model.tooltype;

import com.github.djbamba.db0921.model.ToolType;
import java.math.BigDecimal;

public class Ladder implements ToolType {
  private final BigDecimal rate = new BigDecimal("1.99");

  @Override
  public String type() {
    return "Ladder";
  }

  @Override
  public BigDecimal dailyCharge() {
    return rate;
  }

  @Override
  public boolean weekdayCharge() {
    return true;
  }

  @Override
  public boolean weekendCharge() {
    return true;
  }

}
