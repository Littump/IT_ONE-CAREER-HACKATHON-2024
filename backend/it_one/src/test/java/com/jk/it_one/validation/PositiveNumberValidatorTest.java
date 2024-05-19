package com.jk.it_one.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class PositiveNumberValidatorTest {

    private PositiveNumberValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new PositiveNumberValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    void testIsValid_PositiveNumber() {
        assertTrue(validator.isValid("10", context));
    }

    @Test
    void testIsValid_NegativeNumber() {
        assertFalse(validator.isValid("-5", context));
    }

    @Test
    void testIsValid_Zero() {
        assertFalse(validator.isValid("0", context));
    }

    @Test
    void testIsValid_NotANumber() {
        assertFalse(validator.isValid("abc", context));
    }
}