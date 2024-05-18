package com.jk.it_one.validation;
import org.junit.jupiter.api.Test;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositiveNumberValidatorTest.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveNumberConstraintTest {
    String message() default "'value' should be a positive integer or a fractional number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
