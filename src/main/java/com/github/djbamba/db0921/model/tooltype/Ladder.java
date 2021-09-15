package com.github.djbamba.db0921.model.tooltype;

import com.github.djbamba.db0921.model.ToolType;
import com.github.djbamba.db0921.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
  public boolean countChargeDay(LocalDateTime date) {
    if (!DateUtil.isHoliday(date)) {
      switch (date.getDayOfWeek()) {
        case MONDAY:
          return !DateUtil.isHoliday(date.minusDays(1));
        case FRIDAY:
          return !DateUtil.isHoliday(date.plusDays(1));
        default:
          return true;
      }
    }
    return false;
  }
}
