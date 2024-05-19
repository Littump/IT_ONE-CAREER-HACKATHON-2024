package com.jk.it_one.utils;

import com.jk.it_one.enums.Currency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyCalculatorTest {
    @Test
    void testAdd() {
        String sum1 = "0.12";
        String sum2 = "0.15";
        String result = MoneyCalculator.add(sum1, sum2);
        assertEquals("0.27", result);
    }

    @Test
    void testSub() {
        String sum1 = "0.12";
        String sum2 = "0.15";
        String result = MoneyCalculator.sub(sum1, sum2);
        assertEquals("-0.03", result);
    }

    @Test
    void testCompare() {
        int result1 = MoneyCalculator.compare("30.75", "20.25");
        int result2 = MoneyCalculator.compare("20.25", "30.75");
        int result3 = MoneyCalculator.compare("30.75", "30.75");
        assertTrue(result1 > 0);
        assertTrue(result2 < 0);
        assertEquals(0, result3);
    }

    @Test
    void testConvert() {
        String result1 = MoneyCalculator.convert(Currency.RUB, Currency.USD, "100");
        String result2 = MoneyCalculator.convert(Currency.USD, Currency.RUB, "100");
        assertEquals(0, new BigDecimal("1.0991").compareTo(new BigDecimal(result1)));
        assertEquals(0, new BigDecimal("9099").compareTo(new BigDecimal(result2)));
    }
}
