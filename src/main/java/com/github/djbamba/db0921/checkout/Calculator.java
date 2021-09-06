package com.github.djbamba.db0921.checkout;

import com.github.djbamba.db0921.model.Rentable;
import com.github.djbamba.db0921.util.DateUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the calculations and business logic for tool rentals.
 */
public class Calculator {
  private static final Logger log = LoggerFactory.getLogger(Calculator.class);

  /**
   * Calculates due date
   *
   * @param checkoutDate date customer checks out rental
   * @param rentalDayCount number of days customer wants to rent
   * @return date when customer returns rental
   */
  public LocalDateTime calculateDueDate(LocalDateTime checkoutDate, int rentalDayCount) {
    log.debug(
        "calculateDueDate checkout:{} rentalDays:{} => {}",
        checkoutDate,
        rentalDayCount,
        checkoutDate.plusDays(rentalDayCount));
    return checkoutDate.plusDays(rentalDayCount);
  }

  /**
   * Counts the number of days customers are charged. Excludes non-applicable days. Starts day after
   * checkout.
   *
   * @param rentable type of rental
   * @param checkoutDate date customer checks out rental
   * @param dueDate date customer return rental
   * @return days customer will be charged
   */
  public int calculateChargeableDays(Rentable rentable, LocalDateTime checkoutDate, LocalDateTime dueDate) {
    int chargeableDays = 0;
    LocalDateTime chargeDate = checkoutDate.plusDays(1);

    while (!chargeDate.isAfter(dueDate)) {

      if (shouldCharge(rentable, chargeDate)) {
        chargeableDays += 1;
      }
      chargeDate = chargeDate.plusDays(1);
    }

    return chargeableDays;
  }

  /** Pre-discount charge = dailyRate * chargeDays */
  public BigDecimal calculatePreDiscountRate(BigDecimal dailyRate, int chargeDays) {
    return dailyRate.multiply(BigDecimal.valueOf(chargeDays)).setScale(2, RoundingMode.HALF_UP);
  }

  /** Discount amount = preDiscountRate * discountRate. */
  public BigDecimal calculateDiscountAmount(BigDecimal preDiscountRate, BigDecimal discountRate) {
    // new BigDecimal("0") is not equal to BigDecimal.ZERO so use compareTo
    if (BigDecimal.ZERO.compareTo(discountRate) == 0) return BigDecimal.ZERO;

    return preDiscountRate.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * Determines if a day is chargeable based on rental type.
   *
   * @param rentable type of tool
   * @param checkDate date to check
   * @return true if checkDate is a chargeable day
   */
  private boolean shouldCharge(Rentable rentable, LocalDateTime checkDate) {
    if (DateUtil.isWeekday(checkDate)) {
      // if holiday falls on weekday and applicable
      if (DateUtil.isHoliday(checkDate)) {
        return rentable.isHolidayCharge();
      }
      // if holiday fell on weekend and applicable.
      switch (checkDate.getDayOfWeek()) {
        case MONDAY: // check SUN
          if (DateUtil.isHoliday(checkDate.minusDays(1))) return rentable.isHolidayCharge();
        case FRIDAY: // check SAT
          if (DateUtil.isHoliday(checkDate.plusDays(1))) return rentable.isHolidayCharge();
        default: // cascades if not short-circ'd by MON/FRI cases
          return rentable.isWeekdayCharge();
      }
    }
    // holidays observed on weekdays only. only checking for applicable non-holiday weekends.
    return !DateUtil.isHoliday(checkDate) && rentable.isWeekendCharge();
  }
}
