package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.Period;
import lombok.Data;

import java.util.Date;

@Data
public class IncomePeriodDto {
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private IncomeKind kind;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_day")
    private Date startDay;

    @JsonProperty("period_value")
    private int periodValue;

    @JsonProperty("period_kind")
    private Period periodKind;
}
