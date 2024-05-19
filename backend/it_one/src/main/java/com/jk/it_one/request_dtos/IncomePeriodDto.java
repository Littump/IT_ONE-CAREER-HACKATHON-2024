package com.jk.it_one.request_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.Period;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class IncomePeriodDto {
    @NotBlank(message = "'value' shouldn't be empty")
    @PositiveNumberConstraint
    @JsonProperty("value")
    private String value;

    @NotNull(message = "'kind' shouldn't be null")
    @JsonProperty("kind")
    private IncomeKind kind;

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
