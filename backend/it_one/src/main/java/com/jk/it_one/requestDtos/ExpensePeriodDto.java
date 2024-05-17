package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.enums.Period;
import lombok.Data;

import java.util.Date;

@Data
public class ExpensePeriodDto {
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private ExpenseKind kind;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_day")
    private Date startDay;

    @JsonProperty("period_value")
    private int periodValue;

    @JsonProperty("period_kind")
    private Period periodKind;
}
