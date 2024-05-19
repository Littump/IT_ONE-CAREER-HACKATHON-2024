package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.interfaces.Operation;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.request_dtos.IncomeDto;
import com.jk.it_one.request_dtos.IncomePatchDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "incomes")
public class Income implements Operation<Income> {
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

    public Income(IncomeDto incomeDto) {
        this.value = incomeDto.getValue();
        this.kind = incomeDto.getKind();
        this.description = incomeDto.getDescription();
        this.date = incomeDto.getDate();
    }

    public Income(IncomePatchDto incomePatchDto) {
        this.value = incomePatchDto.getValue();
        this.kind = incomePatchDto.getKind();
        this.description = incomePatchDto.getDescription();
        this.date = incomePatchDto.getDate();
    }

    public Income(IncomePeriod incomePeriod) {
        this.value = incomePeriod.getValue();
        this.kind = incomePeriod.getKind();
        this.description = incomePeriod.getDescription();
        this.date = new Date();
    }

    @Override
    public void patch(Income income) {
        this.value = Objects.requireNonNullElse(income.getValue(), this.value);
        this.kind = Objects.requireNonNullElse(income.getKind(), this.kind);
        this.description = Objects.requireNonNullElse(income.getDescription(), this.description);
        this.date = Objects.requireNonNullElse(income.getDate(), this.date);
    }
}
