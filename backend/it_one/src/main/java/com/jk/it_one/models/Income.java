package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.requestDtos.IncomeDto;
import com.jk.it_one.requestDtos.IncomePatchDto;
import com.jk.it_one.requestDtos.IncomeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "incomes")
public class Income implements WithBalanceAndValue<Income> {
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

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "kind", nullable = false)
    private IncomeKind kind;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    private Date date;


    public Income() {
    }

    public Income(IncomeDto incomeDto) {
        this.value = incomeDto.getValue();
        this.kind = incomeDto.getKind();
        this.description = incomeDto.getDescription();
        this.date = incomeDto.getDate();
    }

    public Income(IncomePatchDto incomeDto) {
        this.value = incomeDto.getValue();
        this.kind = incomeDto.getKind();
        this.description = incomeDto.getDescription();
        this.date = incomeDto.getDate();
    }

    public Income(IncomePeriod incomePeriod) {
        this.value = incomePeriod.getValue();
        this.kind = incomePeriod.getKind();
        this.description = incomePeriod.getDescription();
        this.date = new Date();
    }

    @Override
    public void patch(Income newValue) {
        this.value = Objects.requireNonNullElse(newValue.getValue(), this.value);
        this.kind = Objects.requireNonNullElse(newValue.getKind(), this.kind);
        this.description = Objects.requireNonNullElse(newValue.getDescription(), this.description);
        this.date = Objects.requireNonNullElse(newValue.getDate(), this.date);
    }
}
