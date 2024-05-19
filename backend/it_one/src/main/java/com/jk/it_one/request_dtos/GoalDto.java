package com.jk.it_one.request_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.GoalKind;
import com.jk.it_one.validation.PositiveNumberConstraint;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class GoalDto {
    @NotBlank(message = "'value' shouldn't be empty")
    @PositiveNumberConstraint
    @JsonProperty("goal_value")
    private String goalValue;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "'kind' shouldn't be null")
    @JsonProperty("kind")
    private GoalKind kind;

    @NotNull(message = "'deadline' shouldn't be null")
    @FutureOrPresent
    @JsonProperty("deadline")
    private Date deadline;
}
