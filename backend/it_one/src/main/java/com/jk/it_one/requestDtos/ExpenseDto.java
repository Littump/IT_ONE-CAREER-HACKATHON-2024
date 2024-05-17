package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.enums.IncomeKind;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseDto {
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private ExpenseKind kind;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private Date date;
}
