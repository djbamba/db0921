package com.github.djbamba.db0921.checkout;

import com.github.djbamba.db0921.exception.DiscountRateException;
import com.github.djbamba.db0921.exception.RentalDurationException;

/**
 * Handles pre-calculation validations.
 */
public class CheckoutValidator {
  private static final int MIN_RENTAL_DAYS = 1;
  private static final int MIN_DISCOUNT_RATE = 0;
  private static final int MAX_DISCOUNT_RATE = 100;

  public void validateRentalDays(int rentalDays) {
    if (rentalDays < MIN_RENTAL_DAYS) {
      throw new RentalDurationException(rentalDays, MIN_RENTAL_DAYS);
    }
  }

  public void validateDiscountRate(int discountRate) {
    if (discountRate < MIN_DISCOUNT_RATE || discountRate > MAX_DISCOUNT_RATE) {
      throw new DiscountRateException(discountRate, MIN_DISCOUNT_RATE, MAX_DISCOUNT_RATE);
    }
  }

}
