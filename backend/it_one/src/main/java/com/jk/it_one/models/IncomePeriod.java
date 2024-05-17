package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.Interfaces.WithBalanceValueAndStartDay;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.Period;
import com.jk.it_one.requestDtos.IncomePeriodDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "incomes_period")
public class IncomePeriod implements WithBalanceValueAndStartDay<IncomePeriod> {
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
    private IncomeKind kind;

    @Column(name = "description")
    private String description;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "period_value")
    private int periodValue;

    @Column(name = "period_kind")
    private Period periodKind;

    public IncomePeriod() {
    }

    public IncomePeriod(IncomePeriodDto incomePeriodDto) {
        this.value = incomePeriodDto.getValue();
        this.kind = incomePeriodDto.getKind();
        this.description = incomePeriodDto.getDescription();
        this.startDay = incomePeriodDto.getStartDay();
        this.periodValue = incomePeriodDto.getPeriodValue();
        this.periodKind = incomePeriodDto.getPeriodKind();
    }
}