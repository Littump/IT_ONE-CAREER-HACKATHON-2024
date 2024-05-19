package com.jk.it_one.responce_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.enums.OperationKind;
import com.jk.it_one.enums.Period;
import com.jk.it_one.models.*;
import lombok.Data;

import java.util.Date;

@Data
public class OperationPeriodDto {
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

    @JsonProperty("start_day")
    private Date startDay;

    @JsonProperty("period_value")
    private int periodValue;

    @JsonProperty("period_kind")
    private Period periodKind;

    public OperationPeriodDto(IncomePeriod incomePeriod) {
        this.operationKind = OperationKind.INCOME;
        this.id = incomePeriod.getId();
        this.value = incomePeriod.getValue();
        this.kind = incomePeriod.getKind().name();
        this.description = incomePeriod.getDescription();
        this.startDay = incomePeriod.getStartDay();
        this.periodValue = incomePeriod.getPeriodValue();
        this.periodKind = incomePeriod.getPeriodKind();
    }

    public OperationPeriodDto(ExpensePeriod expensePeriod) {
        this.operationKind = OperationKind.EXPENSE;
        this.id = expensePeriod.getId();
        this.value = expensePeriod.getValue();
        this.kind = expensePeriod.getKind().name();
        this.description = expensePeriod.getDescription();
        this.startDay = expensePeriod.getStartDay();
        this.periodValue = expensePeriod.getPeriodValue();
        this.periodKind = expensePeriod.getPeriodKind();
    }
}
