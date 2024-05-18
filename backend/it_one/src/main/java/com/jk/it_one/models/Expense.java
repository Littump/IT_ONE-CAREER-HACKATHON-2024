package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.requestDtos.ExpenseDto;
import com.jk.it_one.requestDtos.ExpensePatchDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "expenses")
public class Expense implements WithBalanceAndValue<Expense> {
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
    private ExpenseKind kind;

    @Column(name = "description")
    private String description;

    @Column(name = "date", nullable = false)
    private Date date;


    public Expense() {
    }

    public Expense(ExpenseDto expenseDto) {
        this.value = expenseDto.getValue();
        this.kind = expenseDto.getKind();
        this.description = expenseDto.getDescription();
        this.date = expenseDto.getDate();
    }

    public Expense(ExpensePatchDto expenseDto) {
        this.value = expenseDto.getValue();
        this.kind = expenseDto.getKind();
        this.description = expenseDto.getDescription();
        this.date = expenseDto.getDate();
    }

    public Expense(ExpensePeriod expensePeriod) {
        this.value = expensePeriod.getValue();
        this.kind = expensePeriod.getKind();
        this.description = expensePeriod.getDescription();
        this.date = new Date();
    }

    @Override
    public void patch(Expense newValue) {
        this.value = Objects.requireNonNullElse(newValue.getValue(), this.value);
        this.kind = Objects.requireNonNullElse(newValue.getKind(), this.kind);
        this.description = Objects.requireNonNullElse(newValue.getDescription(), this.description);
        this.date = Objects.requireNonNullElse(newValue.getDate(), this.date);
    }
}
