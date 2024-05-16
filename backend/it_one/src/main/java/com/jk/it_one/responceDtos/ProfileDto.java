package com.jk.it_one.responceDtos;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private String username;
    private String name;
    private String balance;
    private String allBalance;
    private List<OperationDto> operations;
    private List<GoalDto> goals;
}
