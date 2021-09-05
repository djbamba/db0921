package com.github.djbamba.db0921;

import static org.junit.Assert.assertEquals;

import com.github.djbamba.db0921.checkout.RentalAgreement;
import com.github.djbamba.db0921.exception.DiscountRateException;
import com.github.djbamba.db0921.exception.NoSuchToolException;
import com.github.djbamba.db0921.exception.RentalDurationException;
import com.github.djbamba.db0921.model.Brand;
import com.github.djbamba.db0921.model.ToolType;
import com.github.djbamba.db0921.util.DateUtil;
import java.math.BigDecimal;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolRentalTest {
  private final RentalApplication toolRentalApp = new RentalApplication();
  private final Logger log = LoggerFactory.getLogger(ToolRentalTest.class);

  @Test(expected = NoSuchToolException.class)
  public void invalidToolCodeCheckoutTest() {
    toolRentalApp.checkout("XXX0", 2, 0, "9/1/15");
  }

  @Test(expected = RentalDurationException.class)
  public void  zeroRentalDayCheckoutTest() {
    toolRentalApp.checkout("JAKR", 0, 5, "9/3/15");
  }

  @Test(expected = DiscountRateException.class)
  public void checkoutTestOne() {
    toolRentalApp.checkout("JAKR", 5, 101, "9/3/15");
  }

  @Test
  public void checkoutTestTwo() {
    RentalAgreement expected =
        RentalAgreement.builder()
            .toolCode("LADW")
            .toolType(ToolType.LADDER.name())
            .toolBrand(Brand.WERNER.name())
            .rentalDays(3)
            .checkoutDate(DateUtil.dateFromString("7/2/20"))
            .dueDate(DateUtil.dateFromString("7/5/20"))
            .chargeDays(1)
            .discountPercent(new BigDecimal("10"))
            .dailyRentalCharge(ToolType.LADDER.getDailyCharge())
            .preDiscountCharge(new BigDecimal("1.99"))
            .discountAmount(new BigDecimal(".20"))
            .finalCharge(new BigDecimal("1.79"))
            .build();

    RentalAgreement actual = toolRentalApp.checkout("LADW", 3, 10, "7/2/20");
    actual.prettyPrint();

    assertEquals("Checkout date", expected.getCheckoutDate(), actual.getCheckoutDate());
    assertEquals("Due date", expected.getDueDate(), actual.getDueDate());
    assertEquals("Chargeable days", expected.getChargeDays(), actual.getChargeDays());
    assertEquals(
        "Pre-discount charge", expected.getPreDiscountCharge(), actual.getPreDiscountCharge());
    assertEquals("Discount amount", expected.getDiscountAmount(), actual.getDiscountAmount());
    assertEquals("Final charge", expected.getFinalCharge(), actual.getFinalCharge());
  }

  @Test
  public void checkoutTestThree() {
    RentalAgreement expected =
        RentalAgreement.builder()
            .toolCode("CHNS")
            .toolType(ToolType.CHAINSAW.name())
            .toolBrand(Brand.STIHL.name())
            .rentalDays(5)
            .checkoutDate(DateUtil.dateFromString("7/2/15"))
            .dueDate(DateUtil.dateFromString("7/7/15"))
            .chargeDays(3)
            .preDiscountCharge(new BigDecimal("4.47"))
            .discountPercent(new BigDecimal("25"))
            .discountAmount(new BigDecimal("1.12"))
            .finalCharge(new BigDecimal("3.35"))
            .build();

    RentalAgreement actual = toolRentalApp.checkout("CHNS", 5, 25, "7/2/15");
    actual.prettyPrint();
    assertEquals("Checkout date", expected.getCheckoutDate(), actual.getCheckoutDate());
    assertEquals("Due date", expected.getDueDate(), actual.getDueDate());
    assertEquals("Chargeable days", expected.getChargeDays(), actual.getChargeDays());
    assertEquals(
        "Pre-discount charge", expected.getPreDiscountCharge(), actual.getPreDiscountCharge());
    assertEquals("Discount amount", expected.getDiscountAmount(), actual.getDiscountAmount());
    assertEquals("Final charge", expected.getFinalCharge(), actual.getFinalCharge());
  }

  @Test
  public void checkoutTestFour() {
    RentalAgreement expected =
        RentalAgreement.builder()
            .toolCode("JAKD")
            .toolType(ToolType.JACKHAMMER.name())
            .toolBrand(Brand.DEWALT.name())
            .rentalDays(6)
            .checkoutDate(DateUtil.dateFromString("9/3/15"))
            .dueDate(DateUtil.dateFromString("9/9/15"))
            .chargeDays(3)
            .discountPercent(BigDecimal.ZERO)
            .dailyRentalCharge(ToolType.JACKHAMMER.getDailyCharge())
            .preDiscountCharge(new BigDecimal("8.97"))
            .discountAmount(BigDecimal.ZERO)
            .finalCharge(new BigDecimal("8.97"))
            .build();

    RentalAgreement actual = toolRentalApp.checkout("JAKD", 6, 0, "9/3/15");
    actual.prettyPrint();

    assertEquals("Checkout date", expected.getCheckoutDate(), actual.getCheckoutDate());
    assertEquals("Due date", expected.getDueDate(), actual.getDueDate());
    assertEquals("Chargeable days", expected.getChargeDays(), actual.getChargeDays());
    assertEquals(
        "Pre-discount charge", expected.getPreDiscountCharge(), actual.getPreDiscountCharge());
    assertEquals("Discount amount", expected.getDiscountAmount(), actual.getDiscountAmount());
    assertEquals("Final charge", expected.getFinalCharge(), actual.getFinalCharge());
  }

  @Test
  public void checkoutTestFive() {
    RentalAgreement expected =
        RentalAgreement.builder()
            .toolCode("JAKR")
            .toolBrand(Brand.RIDGID.name())
            .toolType(ToolType.JACKHAMMER.name())
            .checkoutDate(DateUtil.dateFromString("7/2/15"))
            .dueDate(DateUtil.dateFromString("7/11/15"))
            .rentalDays(9)
            .chargeDays(5)
            .dailyRentalCharge(ToolType.JACKHAMMER.getDailyCharge())
            .discountPercent(BigDecimal.ZERO)
            .discountAmount(BigDecimal.ZERO)
            .preDiscountCharge(new BigDecimal("14.95"))
            .finalCharge(new BigDecimal("14.95"))
            .build();

    RentalAgreement actual = toolRentalApp.checkout("JAKR", 9, 0, "7/2/15");
    actual.prettyPrint();

    assertEquals("Checkout date", expected.getCheckoutDate(), actual.getCheckoutDate());
    assertEquals("Due date", expected.getDueDate(), actual.getDueDate());
    assertEquals("Chargeable days", expected.getChargeDays(), actual.getChargeDays());
    assertEquals(
        "Pre-discount charge", expected.getPreDiscountCharge(), actual.getPreDiscountCharge());
    assertEquals("Discount amount", expected.getDiscountAmount(), actual.getDiscountAmount());
    assertEquals("Final charge", expected.getFinalCharge(), actual.getFinalCharge());
  }

  @Test
  public void checkoutTestSix() {
    RentalAgreement expected =
        RentalAgreement.builder()
            .toolCode("JAKR")
            .toolType(ToolType.JACKHAMMER.name())
            .toolBrand(Brand.RIDGID.name())
            .rentalDays(4)
            .checkoutDate(DateUtil.dateFromString("7/2/20"))
            .dueDate(DateUtil.dateFromString("7/6/20"))
            .chargeDays(1)
            .discountPercent(new BigDecimal("50"))
            .dailyRentalCharge(ToolType.JACKHAMMER.getDailyCharge())
            .preDiscountCharge(new BigDecimal("2.99"))
            .discountAmount(new BigDecimal("1.50"))
            .finalCharge(new BigDecimal("1.49"))
            .build();

    RentalAgreement actual = toolRentalApp.checkout("JAKR", 4, 50, "7/2/20");
    actual.prettyPrint();

    assertEquals("Checkout date", expected.getCheckoutDate(), actual.getCheckoutDate());
    assertEquals("Due date", expected.getDueDate(), actual.getDueDate());
    assertEquals("Chargeable days", expected.getChargeDays(), actual.getChargeDays());
    assertEquals(
        "Pre-discount charge", expected.getPreDiscountCharge(), actual.getPreDiscountCharge());
    assertEquals("Discount amount", expected.getDiscountAmount(), actual.getDiscountAmount());
    assertEquals("Final charge", expected.getFinalCharge(), actual.getFinalCharge());
  }
}
