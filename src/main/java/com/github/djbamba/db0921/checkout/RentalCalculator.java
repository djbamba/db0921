package com.github.djbamba.db0921.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RentalCalculator {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final int discountRate;
  protected LocalDateTime checkoutDate;
  protected int rentalDays;
  protected int chargeableDays;
  protected BigDecimal preDiscountRate;
  protected BigDecimal discountPercent;
  protected BigDecimal discountAmount;
  protected LocalDateTime dueDate;
  protected BigDecimal finalCharge;

  public RentalCalculator(LocalDateTime checkoutDate, int rentalDays, int discountRate) {
    this.checkoutDate = checkoutDate;
    this.rentalDays = rentalDays;
    this.discountRate = discountRate;
    this.discountPercent = BigDecimal.valueOf(discountRate).movePointLeft(2);
  }

  public RentalCalculator calculateDueDate() {
    this.dueDate = this.checkoutDate.plusDays(rentalDays);
    return this;
  }

  public abstract RentalCalculator calculateChargeableDays();

  public RentalCalculator calculatePreDiscountRate(BigDecimal dailyRate) {
    this.preDiscountRate = setScale(dailyRate.multiply(BigDecimal.valueOf(chargeableDays)));

    return this;
  }

  public RentalCalculator calculateDiscountAmount() {
    this.discountAmount =
        BigDecimal.ZERO.compareTo(BigDecimal.valueOf(discountRate)) == 0
            ? BigDecimal.ZERO
            : setScale(preDiscountRate.multiply(discountPercent));

    return this;
  }

  public RentalCalculator calculateFinalCharge() {
    finalCharge = this.preDiscountRate.subtract(discountAmount);

    return this;
  }

  public abstract RentalAgreement generateAgreement();

  protected BigDecimal setScale(BigDecimal value) {
    return value.setScale(2, RoundingMode.HALF_UP);
  }
}
