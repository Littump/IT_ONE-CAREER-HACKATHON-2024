package com.jk.it_one.responceDtos;

import com.jk.it_one.enums.KindOperation;
import lombok.Data;

import java.util.Date;

@Data
public class OperationDto {
    private KindOperation kindOperation;
    private Long id;
    private String value;
    private String kind;
    private String description;
    private Date date;
}
