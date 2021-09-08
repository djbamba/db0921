package com.github.djbamba.db0921.model;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public enum ToolType {
  // FIXME: re-visit. should implement interface like brand?
  LADDER(new BigDecimal("1.99"), true, true, false),
  CHAINSAW(new BigDecimal("1.49"), true, false, true),
  JACKHAMMER(new BigDecimal("2.99"), true, false, false);

  private final BigDecimal dailyCharge;
  private final boolean weekdayCharge;
  private final boolean weekendCharge;
  private final boolean holidayCharge;

  ToolType(
      BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
    this.dailyCharge = dailyCharge;
    this.weekdayCharge = weekdayCharge;
    this.weekendCharge = weekendCharge;
    this.holidayCharge = holidayCharge;
  }


}
