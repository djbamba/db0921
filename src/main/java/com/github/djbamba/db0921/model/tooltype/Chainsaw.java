package com.github.djbamba.db0921.model.tooltype;

import com.github.djbamba.db0921.model.ToolType;
import java.math.BigDecimal;

public class Chainsaw implements ToolType {
  private final BigDecimal rate = new BigDecimal("1.49");

  @Override
  public String type() {
    return "Chainsaw";
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
  public boolean holidayCharge() {
    return true;
  }
}
