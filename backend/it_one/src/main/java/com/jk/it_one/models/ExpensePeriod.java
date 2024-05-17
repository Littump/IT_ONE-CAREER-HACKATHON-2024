package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.Interfaces.WithBalanceValueAndStartDay;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.enums.Period;
import com.jk.it_one.requestDtos.ExpensePeriodDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "expenses_period")
public class ExpensePeriod implements WithBalanceValueAndStartDay<ExpensePeriod> {
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

    @Column(name = "value")
    private String value;

    @Column(name = "kind")
    private ExpenseKind kind;

    @Column(name = "description")
    private String description;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "period_value")
    private int periodValue;

    @Column(name = "period_kind")
    private Period periodKind;

    public ExpensePeriod() {
    }

    public ExpensePeriod(ExpensePeriodDto expencePeriodDto) {
        this.value = expencePeriodDto.getValue();
        this.kind = expencePeriodDto.getKind();
        this.description = expencePeriodDto.getDescription();
        this.startDay = expencePeriodDto.getStartDay();
        this.periodValue = expencePeriodDto.getPeriodValue();
        this.periodKind = expencePeriodDto.getPeriodKind();
    }
}
