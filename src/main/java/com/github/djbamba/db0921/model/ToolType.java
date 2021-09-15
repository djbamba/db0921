package com.github.djbamba.db0921.model;

import com.github.djbamba.db0921.model.tooltype.Chainsaw;
import com.github.djbamba.db0921.model.tooltype.Jackhammer;
import com.github.djbamba.db0921.model.tooltype.Ladder;
import java.math.BigDecimal;

public interface ToolType {
  String type();

  BigDecimal dailyCharge();

  default boolean weekdayCharge() {
    return false;
  }

  default boolean weekendCharge() {
    return false;
  }

  default boolean holidayCharge() {
    return false;
  }

  ToolType LADDER = new Ladder();
  ToolType CHAINSAW = new Chainsaw();
  ToolType JACKHAMMER = new Jackhammer();
}
