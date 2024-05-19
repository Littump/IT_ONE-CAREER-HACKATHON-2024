package com.jk.it_one.utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.Date;


class DataTimeUtilsTest {
    @Test
    void testIsCurrentDay() {
        Date date = new Date();
        assertTrue(DataTimeUtils.isCurrentDay(date));

        int not_current_day = 1715948197;
        date = new Date(not_current_day);
        assertFalse(DataTimeUtils.isCurrentDay(date));
    }
}
