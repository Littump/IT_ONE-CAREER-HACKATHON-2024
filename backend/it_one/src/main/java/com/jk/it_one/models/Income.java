package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.Interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.requestDtos.IncomeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "incomes")
public class Income implements WithBalanceAndValue {
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

    @Column(name = "date")
    private Date date;


    public Income() {
    }

    public Income(IncomeDto incomeDto) {
        this.patch(incomeDto);
    }

    public Income(IncomePeriod incomePeriod) {
        this.value = incomePeriod.getValue();
        this.kind = incomePeriod.getKind();
        this.description = incomePeriod.getDescription();
        this.date = new Date();
    }

    public void patch(IncomeDto incomeDto) {
        this.value = incomeDto.getValue();
        this.kind = incomeDto.getKind();
        this.description = incomeDto.getDescription();
        this.date = incomeDto.getDate();
    }
}
