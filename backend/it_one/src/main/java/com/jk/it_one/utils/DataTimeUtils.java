package com.jk.it_one.utils;

import java.util.Calendar;
import java.util.Date;

public class DataTimeUtils {
    public static boolean isCurrentDay(Date startDay) {
        Calendar startDayCalendar = Calendar.getInstance();
        Calendar currentDayCalendar = Calendar.getInstance();
        startDayCalendar.setTime(startDay);
        currentDayCalendar.setTime(new Date());
        return startDayCalendar.get(Calendar.YEAR) == currentDayCalendar.get(Calendar.YEAR) &&
                startDayCalendar.get(Calendar.DAY_OF_YEAR) == currentDayCalendar.get(Calendar.DAY_OF_YEAR);
    }
}
