package com.jk.it_one.request_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoalIncomeDto {
    @NotBlank(message = "'value' shouldn't be empty")
    @PositiveNumberConstraint
    @JsonProperty("value")
    private String value;
}
