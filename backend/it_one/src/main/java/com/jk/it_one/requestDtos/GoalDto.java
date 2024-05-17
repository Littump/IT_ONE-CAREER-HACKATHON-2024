package com.jk.it_one.requestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.GoalKind;
import lombok.Data;

import java.util.Date;

@Data
public class GoalDto {
    @JsonProperty("goal_value")
    private String goalValue;

    @JsonProperty("description")
    private String description;

    @JsonProperty("kind")
    private GoalKind kind;

    @JsonProperty("deadline")
    private Date deadline;
}
