package com.github.djbamba.db0921.checkout;

import com.github.djbamba.db0921.model.Tool;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Handles the calculations and business logic for tool rentals. */
public class CheckoutCalculator {
  private static final Logger log = LoggerFactory.getLogger(CheckoutCalculator.class);
  private LocalDateTime checkoutDate;
  private LocalDateTime dueDate;
  private int rentalDays;
  private BigDecimal discountPercent;
  private Tool rentalTool;

  public CheckoutCalculator(
      Tool rentalTool, LocalDateTime checkoutDate, int rentalDays, int discountRate) {
    this.rentalTool = rentalTool;
    this.checkoutDate = checkoutDate;
    this.rentalDays = rentalDays;
    this.discountPercent = BigDecimal.valueOf(discountRate, -2);
  }

  public LocalDateTime calculateDueDate() {
    this.dueDate = this.checkoutDate.plusDays(rentalDays);
    return this.dueDate;
  }

  public int calculateChargeableDays() {
    int chargeableDays = 0;
    LocalDateTime chargeDate = checkoutDate.plusDays(1);

    while (!chargeDate.isAfter(dueDate)) {
      if (rentalTool.isChargeable(chargeDate)) {
        chargeableDays++;
      }
      chargeDate = chargeDate.plusDays(1);
    }

    return chargeableDays;
  }

  /** Pre-discount charge = dailyRate * chargeDays */
  public BigDecimal calculatePreDiscountRate(BigDecimal dailyRate, int chargeDays) {
    // create new object
    BigDecimal preDiscountRate = dailyRate.multiply(BigDecimal.valueOf(chargeDays));

    return setScale(preDiscountRate);
  }

  /** Discount amount = preDiscountRate * discountRate. */
  public BigDecimal calculateDiscountAmount(BigDecimal preDiscountRate, BigDecimal discountRate) {
    if (BigDecimal.ZERO.compareTo(discountRate) == 0) return BigDecimal.ZERO;

    BigDecimal discountAmount = preDiscountRate.multiply(discountRate);
    return setScale(discountAmount);
  }

  private BigDecimal setScale(BigDecimal value) {
    return value.setScale(2, RoundingMode.HALF_UP);
  }
}
