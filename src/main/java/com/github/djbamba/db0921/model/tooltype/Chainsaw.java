package com.github.djbamba.db0921.model.tooltype;

import com.github.djbamba.db0921.model.ToolType;
import com.github.djbamba.db0921.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
  public boolean countChargeDay(LocalDateTime date) {
    return !DateUtil.isWeekend(date);
  }
}
