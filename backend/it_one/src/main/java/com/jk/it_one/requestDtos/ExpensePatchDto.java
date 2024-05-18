package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class ExpensePatchDto {
    @PositiveNumberConstraint
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private ExpenseKind kind;

    @JsonProperty("description")
    private String description;

    @PastOrPresent
    @JsonProperty("date")
    private Date date;
}
