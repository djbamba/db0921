package com.github.djbamba.db0921.exception;

public class RentalDurationException extends RuntimeException {

  public RentalDurationException(int rentalDays, int minRentalDays) {
    super(
        String.format(
            "The number of rental days specified [%d] is invalid.\nProvide a number of %d or more rental days to continue.",
            rentalDays, minRentalDays));
  }
}
