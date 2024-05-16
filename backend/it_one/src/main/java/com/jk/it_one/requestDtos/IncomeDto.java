package com.jk.it_one.requestDtos;

import com.jk.it_one.enums.IncomeKind;
import lombok.Data;

import java.util.Date;

@Data
public class IncomeDto {
    private String value;
    private IncomeKind kind;
    private String description;
    private Date date;
}
