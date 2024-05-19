package com.jk.it_one.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

class DataTimeUtilsTest {

    @Test
    void testIsCurrentDay_CurrentDay() {
        Date currentDate = new Date();
        boolean result = DataTimeUtils.isCurrentDay(currentDate);
        assertTrue(result);
    }

    @Test
    void testIsCurrentDay_NotCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date previousDay = calendar.getTime();
        boolean result = DataTimeUtils.isCurrentDay(previousDay);
        assertFalse(result);
    }
}
