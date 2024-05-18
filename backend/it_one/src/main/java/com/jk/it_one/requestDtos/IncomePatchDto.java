package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class IncomePatchDto {
    @PositiveNumberConstraint
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private IncomeKind kind;

    @JsonProperty("description")
    private String description;

    @PastOrPresent
    @JsonProperty("date")
    private Date date;
}
