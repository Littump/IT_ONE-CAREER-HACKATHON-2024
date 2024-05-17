package com.jk.it_one.utils;

import java.math.BigDecimal;

public class MoneyCalculator {
    public static String add(String sum1, String sum2) {
        return new BigDecimal(sum1).add(new BigDecimal(sum2)).toString();
    }

    public static String sub(String sum1, String sum2) {
        return new BigDecimal(sum1).subtract(new BigDecimal(sum2)).toString();
    }

    public static int compare(String sum1, String sum2) {
        return new BigDecimal(sum1).compareTo(new BigDecimal(sum2));
    }
}
