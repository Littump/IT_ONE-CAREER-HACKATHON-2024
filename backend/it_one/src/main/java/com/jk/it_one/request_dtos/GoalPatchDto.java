package com.jk.it_one.request_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.GoalKind;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.util.Date;

@Data
public class GoalPatchDto {
    @PositiveNumberConstraint
    @JsonProperty("goal_value")
    private String goalValue;

    @JsonProperty("description")
    private String description;

    @JsonProperty("kind")
    private GoalKind kind;

    @FutureOrPresent
    @JsonProperty("deadline")
    private Date deadline;
}
