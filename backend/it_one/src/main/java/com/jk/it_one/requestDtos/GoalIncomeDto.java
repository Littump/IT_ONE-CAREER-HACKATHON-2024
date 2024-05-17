package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoalIncomeDto {
    @JsonProperty("value")
    private String value;
}
