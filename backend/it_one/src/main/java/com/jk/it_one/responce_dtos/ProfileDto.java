package com.jk.it_one.responce_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Goal;
import com.jk.it_one.models.User;
import com.jk.it_one.utils.MoneyCalculator;
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

    public ProfileDto(
            User user,
            Balance balance,
            List<Balance> balances,
            List<OperationDto> operations,
            List<Goal> goals
    ) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.balance = balance.getValue();
        this.allBalance = balances.stream()
                .map(b -> MoneyCalculator.convert(b.getCurrency(), balance.getCurrency(), b.getValue()))
                .reduce(MoneyCalculator::add).orElse("0");
        this.operations = operations;
        this.goals = goals.stream().map(GoalDto::new).toList();
    }
}
