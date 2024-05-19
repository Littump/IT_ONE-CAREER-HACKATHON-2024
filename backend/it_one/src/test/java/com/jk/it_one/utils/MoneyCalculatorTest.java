package com.jk.it_one.utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
