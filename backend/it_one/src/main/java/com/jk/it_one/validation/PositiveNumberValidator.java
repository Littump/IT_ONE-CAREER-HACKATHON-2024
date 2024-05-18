package com.jk.it_one.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PositiveNumberValidator implements
        ConstraintValidator<PositiveNumberConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            BigDecimal number = new BigDecimal(s);
            return number.compareTo(BigDecimal.ZERO) > 0;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
