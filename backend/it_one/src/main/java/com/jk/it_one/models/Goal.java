package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.Interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.GoalKind;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.requestDtos.GoalDto;
import com.jk.it_one.requestDtos.IncomeDto;
import com.jk.it_one.utils.MoneyCalculator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goal_value")
    private String goalValue;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "kind")
    private GoalKind kind;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "achieved")
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

    public void patch(Goal goal) {
        this.goalValue = goal.getGoalValue();
        this.description = goal.getDescription();
        this.kind = goal.getKind();
        this.deadline = goal.getDeadline();
        this.achieved = MoneyCalculator.compare(value, goalValue) >= 0;
    }
}
