package com.github.djbamba.db0921.checkout;

import com.github.djbamba.db0921.checkout.RentalAgreement.RentalAgreementBuilder;
import com.github.djbamba.db0921.dao.ToolRentalDao;
import com.github.djbamba.db0921.exception.NoSuchToolException;
import com.github.djbamba.db0921.model.Tool;
import java.time.LocalDateTime;

/** Handles validations and calculations */
public class CheckoutProcessor {
  private final CheckoutValidator checkoutValidator = new CheckoutValidator();
  private final ToolRentalDao toolCatalog = new ToolRentalDao();
  private RentalAgreementBuilder agreementBuilder;

  public RentalAgreement process(
      String toolCode, int rentalDayCount, int discountRate, LocalDateTime checkoutDate) {
    // check if tool exists before proceeding
    Tool tool = toolCatalog.findByID(toolCode).orElseThrow(() -> new NoSuchToolException(toolCode));
    agreementBuilder = RentalAgreement.builder();
    // pre-calc validations
    checkoutValidator.validateRentalDays(rentalDayCount);
    checkoutValidator.validateDiscountRate(discountRate);
    // TODO: calculations
    return agreementBuilder.build();
  }
}
