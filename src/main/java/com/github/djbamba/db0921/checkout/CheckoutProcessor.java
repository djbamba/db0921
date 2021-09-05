package com.github.djbamba.db0921.checkout;

import com.github.djbamba.db0921.checkout.RentalAgreement.RentalAgreementBuilder;
import com.github.djbamba.db0921.dao.ToolRentalDao;
import com.github.djbamba.db0921.exception.NoSuchToolException;
import com.github.djbamba.db0921.model.Tool;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Handles validations and calculations */
public class CheckoutProcessor {
  private final CheckoutValidator checkoutValidator = new CheckoutValidator();
  private final Calculator calculator = new Calculator();
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
    BigDecimal discRate = convertIntToBigDecimal(discountRate).movePointLeft(2);
    populateGeneralInfo(tool, rentalDayCount, discRate, checkoutDate);
    // begin calcs
    LocalDateTime dueDate = calculator.calculateDueDate(checkoutDate, rentalDayCount);
    int chargeDays = calculator.calculateChargeableDays(tool.getToolType(), checkoutDate, dueDate);
    BigDecimal preDiscount =
        calculator.calculatePreDiscountRate(tool.getToolType().getDailyCharge(), chargeDays);
    BigDecimal discountAmount = calculator.calculateDiscountAmount(preDiscount, discRate);

    agreementBuilder
        .dueDate(dueDate)
        .chargeDays(chargeDays)
        .preDiscountCharge(preDiscount)
        .discountAmount(discountAmount)
        .finalCharge(preDiscount.subtract(discountAmount));

    return agreementBuilder.build();
  }

  /** Populate RentalAgreement with given  info*/
  private void populateGeneralInfo(
      Tool tool, int rentalDayCount, BigDecimal discountRate, LocalDateTime checkoutDate) {
    agreementBuilder
        .toolCode(tool.getToolCode())
        .toolType(tool.getToolType().name())
        .toolBrand(tool.getBrand().toString())
        .dailyRentalCharge(tool.getToolType().getDailyCharge())
        .rentalDays(rentalDayCount)
        .checkoutDate(checkoutDate)
        .discountPercent(discountRate);
  }

  private BigDecimal convertIntToBigDecimal(int convertVal) {
    // handle when '0' => '0.00'
    if (convertVal == 0) {
      return BigDecimal.ZERO;
    }
    return BigDecimal.valueOf(convertVal);
  }
}
