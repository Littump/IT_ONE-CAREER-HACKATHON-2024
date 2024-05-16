package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.Period;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "incomes_period")
public class IncomePeriod {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "kind")
    private IncomeKind kind;

    @Column(name = "description")
    private String description;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "period_value")
    private int periodValue;

    @Column(name = "period_kind")
    private Period periodKind;
}
