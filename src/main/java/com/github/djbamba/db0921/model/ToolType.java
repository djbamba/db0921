package com.github.djbamba.db0921.model;

import com.github.djbamba.db0921.model.tooltype.Chainsaw;
import com.github.djbamba.db0921.model.tooltype.Jackhammer;
import com.github.djbamba.db0921.model.tooltype.Ladder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ToolType {
  ToolType LADDER = new Ladder();
  ToolType CHAINSAW = new Chainsaw();
  ToolType JACKHAMMER = new Jackhammer();

  String type();

  BigDecimal dailyCharge();

  boolean countChargeDay(LocalDateTime date);
}
