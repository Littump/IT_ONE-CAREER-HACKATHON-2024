package com.jk.it_one.responceDtos;

import com.jk.it_one.enums.GoalKind;
import lombok.Data;

import java.util.Date;

@Data
public class GoalDto {
    private Long id;
    private String goalValue;
    private String value;
    private GoalKind kind;
    private Date deadline;
    private String description;
    private boolean isAchievement;
}
