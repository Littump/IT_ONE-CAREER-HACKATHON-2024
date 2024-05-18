package com.jk.it_one.utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyCalculatorTest {
    @Test
    public void testAdd() {
        String sum1 = "0.12";
        String sum2 = "0.15";
        String result = MoneyCalculator.add(sum1, sum2);
        assertEquals(result, "0.27");
    }

    @Test
    public void testSub() {
        String sum1 = "0.12";
        String sum2 = "0.15";
        String result = MoneyCalculator.sub(sum1, sum2);
        assertEquals(result, "-0.03");
    }
}
