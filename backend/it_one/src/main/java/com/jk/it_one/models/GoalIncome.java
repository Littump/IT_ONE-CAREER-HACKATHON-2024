package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.requestDtos.GoalIncomeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "goals_incomes")
public class GoalIncome {
    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "date", nullable = false)
    private Date date;

    public GoalIncome() {
    }

    public GoalIncome(GoalIncomeDto goalIncomeDto) {
        this.value = goalIncomeDto.getValue();
        this.date = new Date();
    }

    public void patch(GoalIncomeDto goalIncomeDto) {
        this.value = goalIncomeDto.getValue();
    }
}
