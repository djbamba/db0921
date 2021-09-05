package com.github.djbamba.db0921.checkout;

import com.github.djbamba.db0921.util.DateUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Builder
@RequiredArgsConstructor
/*
 * Candidate for abstraction. Convert to generic and remove tool info.
 */
public class RentalAgreement {
  private static final Logger log = LoggerFactory.getLogger(RentalAgreement.class);
  private final NumberFormat currencyFormat = new DecimalFormat("$#,##0.00");
  private final NumberFormat percentFormat = new DecimalFormat("0%");
  // tool info
  private final String toolCode;
  private final String toolType;
  private final String toolBrand;
  // rental info
  private final int rentalDays;
  private final LocalDateTime checkoutDate;
  private final LocalDateTime dueDate;
  private final BigDecimal dailyRentalCharge;
  private final int chargeDays;
  // rates
  private final BigDecimal preDiscountCharge;
  private final BigDecimal discountPercent;
  private final BigDecimal discountAmount;
  private final BigDecimal finalCharge;

  @Override
  public String toString() {
    return "\n==========\n"
        + "Tool code: "
        + toolCode
        + "\n"
        + "Tool type: "
        + toolType
        + "\n"
        + "Tool brand: "
        + toolBrand
        + "\n"
        + "Rental days: "
        + rentalDays
        + '\n'
        + "Checkout date: "
        + DateUtil.dateStringFormatted(checkoutDate)
        + "\n"
        + "Due date: "
        + DateUtil.dateStringFormatted(dueDate)
        + '\n'
        + "Daily rental charge: "
        + currencyFormat.format(dailyRentalCharge)
        + '\n'
        + "Charge days: "
        + chargeDays
        + '\n'
        + "Pre-discount charge: "
        + currencyFormat.format(preDiscountCharge)
        + '\n'
        + "Discount percent: "
        + percentFormat.format(discountPercent)
        + '\n'
        + "Discount amount: "
        + currencyFormat.format(discountAmount)
        + '\n'
        + "Final charge: "
        + currencyFormat.format(finalCharge)
        + '\n'
        + "==========";
  }

  /** Prints pretty RentalAgreement to console */
  public void prettyPrint() {
    log.info("{}", this);
  }
}
