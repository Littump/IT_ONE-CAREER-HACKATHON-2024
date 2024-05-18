package com.jk.it_one.responceDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Goal;
import com.jk.it_one.models.User;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    @JsonProperty("username")
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private String balance;

    @JsonProperty("all_balance")
    private String allBalance;

    @JsonProperty("operations")
    private List<OperationDto> operations;

    @JsonProperty("goals")
    private List<GoalDto> goals;

    public ProfileDto(User user, Balance balance, List<Balance> balances, List<OperationDto> operations, List<Goal> goals) { //TODO перевести всё к одному балансу
        this.username = user.getUsername();
        this.name = user.getName();
        this.balance = balance.getValue();
        this.allBalance = "239";
        this.operations = operations;
        this.goals = goals.stream().map(GoalDto::new).toList();
    }
}
