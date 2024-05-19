package com.jk.it_one.utils;

import com.jk.it_one.enums.Currency;

import java.math.BigDecimal;
import java.util.Map;

public class MoneyCalculator {
    private MoneyCalculator() {
        throw new IllegalStateException("Utility class");
    }

    private static Map<Currency, Map<Currency, String>> convertor = Map.of(
            Currency.RUB, Map.of(
                    Currency.RUB, "1",
                    Currency.USD, "0.010991",
                    Currency.EUR, "0.010124",
                    Currency.CNY, "0.079521"
            ),
            Currency.USD, Map.of(
                    Currency.RUB, "90.99",
                    Currency.USD, "1",
                    Currency.EUR, "0.9223",
                    Currency.CNY, "7.23"
            ),
            Currency.EUR, Map.of(
                    Currency.RUB, "98.78",
                    Currency.USD, "1.08",
                    Currency.EUR, "1",
                    Currency.CNY, "7.83"
            ),
            Currency.CNY, Map.of(
                    Currency.RUB, "12.58",
                    Currency.USD, "0.138385",
                    Currency.EUR, "0.127633",
                    Currency.CNY, "1"
            )
    );

    public static String add(String sum1, String sum2) {
        return new BigDecimal(sum1).add(new BigDecimal(sum2)).toString();
    }

    public static String sub(String sum1, String sum2) {
        return new BigDecimal(sum1).subtract(new BigDecimal(sum2)).toString();
    }

    public static int compare(String sum1, String sum2) {
        return new BigDecimal(sum1).compareTo(new BigDecimal(sum2));
    }

    public static String convert(Currency from, Currency to, String amount) {
        return new BigDecimal(amount).multiply(new BigDecimal(convertor.get(from).get(to))).toString();
    }
}
