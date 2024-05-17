package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.IncomeKind;
import lombok.Data;

import java.util.Date;

@Data
public class IncomeDto {
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private IncomeKind kind;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private Date date;
}
