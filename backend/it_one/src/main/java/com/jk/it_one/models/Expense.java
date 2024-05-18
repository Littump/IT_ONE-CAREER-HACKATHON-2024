package com.jk.it_one.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jk.it_one.interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.requestDtos.ExpenseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @Column(name = "date")
    private Date date;


    public Expense() {
    }

    public Expense(ExpenseDto expenseDto) {
        this.patch(expenseDto);
    }

    public Expense(ExpensePeriod expensePeriod) {
        this.value = expensePeriod.getValue();
        this.kind = expensePeriod.getKind();
        this.description = expensePeriod.getDescription();
        this.date = new Date();
    }

    public void patch(ExpenseDto expenseDto) {
        this.value = expenseDto.getValue();
        this.kind = expenseDto.getKind();
        this.description = expenseDto.getDescription();
        this.date = expenseDto.getDate();
    }

    @Override
    public void patch(Expense newValue) {

    }
}
