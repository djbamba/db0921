package com.github.djbamba.db0921.exception;

public class DiscountRateException extends RuntimeException {

  public DiscountRateException(int discountRate, int minDiscountRate, int maxDiscountRate) {
    super(
        String.format(
            "The specified discount rate [%d] is invalid.\nEnter a value between [%d - %d] to continue.",
            discountRate, minDiscountRate, maxDiscountRate));
  }
}
