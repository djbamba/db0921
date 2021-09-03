package com.github.djbamba.db0921;

import com.github.djbamba.db0921.util.DateUtil;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {
  private final Function<String, LocalDateTime> stringToDate = DateUtil::dateFromString;

  @Test
  public void holidayTest() {
    Assert.assertTrue("Labor Day", DateUtil.isHoliday(stringToDate.apply("9/6/21")));
    Assert.assertFalse("Sept - 2nd Monday", DateUtil.isHoliday(stringToDate.apply("9/13/21")));
    Assert.assertTrue("Independence Day", DateUtil.isHoliday(stringToDate.apply("7/4/20")));
    Assert.assertFalse("New Year", DateUtil.isHoliday(stringToDate.apply("1/1/22")));
  }

  @Test
  public void daysOfTheWeek() {
    Assert.assertTrue("Friday", DateUtil.isWeekday(LocalDateTime.now().with(DayOfWeek.FRIDAY)));
    Assert.assertTrue("Saturday", DateUtil.isWeekend(LocalDateTime.now().with(DayOfWeek.SATURDAY)));
  }

}
