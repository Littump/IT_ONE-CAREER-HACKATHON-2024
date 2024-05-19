package com.jk.it_one.request_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Date;

@Data
public class IncomeDto {
    @NotBlank(message = "'value' shouldn't be empty")
    @PositiveNumberConstraint
    @JsonProperty("value")
    private String value;

    @NotNull(message = "'kind' shouldn't be null")
    @JsonProperty("kind")
    private IncomeKind kind;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "'date' shouldn't be null")
    @PastOrPresent
    @JsonProperty("date")
    private Date date;
}
