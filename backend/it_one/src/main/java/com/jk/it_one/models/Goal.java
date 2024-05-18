package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.GoalKind;
import com.jk.it_one.requestDtos.GoalDto;
import com.jk.it_one.requestDtos.GoalPatchDto;
import com.jk.it_one.utils.MoneyCalculator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "goals")
public class Goal implements WithBalanceAndValue<Goal> {
    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "balance_id", nullable = false)
    private Balance balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goal_value", nullable = false)
    private String goalValue;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "kind", nullable = false)
    private GoalKind kind;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @Column(name = "achieved", nullable = false)
    boolean achieved;

    public Goal() {
    }

    public Goal(GoalDto goalDto) {
        this.value = "0";
        this.goalValue = goalDto.getGoalValue();
        this.description = goalDto.getDescription();
        this.kind = goalDto.getKind();
        this.deadline = goalDto.getDeadline();
        this.achieved = MoneyCalculator.compare(value, goalValue) >= 0;
    }

    public Goal(GoalPatchDto goalDto) {
        this.goalValue = goalDto.getGoalValue();
        this.description = goalDto.getDescription();
        this.kind = goalDto.getKind();
        this.deadline = goalDto.getDeadline();
    }

    public void patch(Goal goal) {
        this.goalValue = Objects.requireNonNullElse(goal.getGoalValue(), this.goalValue);
        this.description = Objects.requireNonNullElse(goal.getDescription(), this.description);
        this.kind = Objects.requireNonNullElse(goal.getKind(), this.kind);
        this.deadline = Objects.requireNonNullElse(goal.getDeadline(), this.deadline);
        this.achieved = MoneyCalculator.compare(value, goalValue) >= 0;
    }
}
