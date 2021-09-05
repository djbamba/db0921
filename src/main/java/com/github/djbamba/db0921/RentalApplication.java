package com.github.djbamba.db0921;

import static com.github.djbamba.db0921.util.DateUtil.dateFromString;

import com.github.djbamba.db0921.checkout.CheckoutProcessor;
import com.github.djbamba.db0921.checkout.RentalAgreement;

public class RentalApplication {
  private final CheckoutProcessor checkoutProcessor = new CheckoutProcessor();

  public RentalAgreement checkout(
      String toolCode, int rentalDayCount, int discountRate, String checkoutDate) {
    return checkoutProcessor.process(
        toolCode, rentalDayCount, discountRate, dateFromString(checkoutDate));
  }
}
