package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.enums.GoalKind;
import com.jk.it_one.enums.IncomeKind;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "goals")
public class Goal {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
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
    private Date date;

    @Column(name = "is_achievement")
    boolean isAchievement;
}
