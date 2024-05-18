package com.jk.it_one.responceDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.KindOperation;
import com.jk.it_one.enums.OperationKind;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Expense;
import com.jk.it_one.models.Income;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class OperationDto {
    @JsonProperty("kind_operation")
    private OperationKind operationKind;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private Date date;

    public OperationDto(Income income) {
        this.operationKind = OperationKind.INCOME;
        this.id = income.getId();
        this.value = income.getValue();
        this.kind = income.getKind().name();
        this.description = income.getDescription();
        this.date = income.getDate();
    }

    public OperationDto(Expense expense) {
        this.operationKind = OperationKind.EXPENSE;
        this.id = expense.getId();
        this.value = expense.getValue();
        this.kind = expense.getKind().name();
        this.description = expense.getDescription();
        this.date = expense.getDate();
    }
}
