package com.jk.it_one.requestDtos;

import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.Period;
import lombok.Data;

import java.util.Date;

@Data
public class IncomePeriodDto {
    private String value;
    private IncomeKind kind;
    private String description;
    private Date startDay;
    private int periodValue;
    private Period periodKind;
}
