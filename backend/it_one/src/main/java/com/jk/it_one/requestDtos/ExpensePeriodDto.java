package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.enums.Period;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class ExpensePeriodDto {
    @NotBlank(message = "'value' shouldn't be empty")
    @PositiveNumberConstraint
    @JsonProperty("value")
    private String value;

    @NotNull(message = "'kind' shouldn't be null")
    @JsonProperty("kind")
    private ExpenseKind kind;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "'start_day' shouldn't be null")
    @FutureOrPresent
    @JsonProperty("start_day")
    private Date startDay;

    @Positive
    @JsonProperty("period_value")
    private int periodValue;

    @NotNull(message = "'period_kind' shouldn't be null")
    @JsonProperty("period_kind")
    private Period periodKind;
}
