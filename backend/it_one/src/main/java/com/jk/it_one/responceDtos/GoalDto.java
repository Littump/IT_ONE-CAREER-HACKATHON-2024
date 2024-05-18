package com.jk.it_one.responceDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.GoalKind;
import com.jk.it_one.models.Goal;
import lombok.Data;

import java.util.Date;

@Data
public class GoalDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("goal_value")
    private String goalValue;

    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private GoalKind kind;

    @JsonProperty("deadline")
    private Date deadline;

    @JsonProperty("description")
    private String description;

    @JsonProperty("achieved")
    private boolean achieved;

    public GoalDto(Goal goal) {
        this.id = goal.getId();
        this.goalValue = goal.getGoalValue();
        this.value = goal.getValue();
        this.kind = goal.getKind();
        this.deadline = goal.getDeadline();
        this.description = goal.getDescription();
        this.achieved = goal.isAchieved();
    }
}
