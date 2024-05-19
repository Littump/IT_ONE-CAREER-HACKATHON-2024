package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.exceptions.NotEnoughMoneyException;
import com.jk.it_one.models.*;
import com.jk.it_one.repositories.GoalIncomeRepository;
import com.jk.it_one.repositories.GoalRepository;
import com.jk.it_one.request_dtos.GoalIncomeDto;
import com.jk.it_one.utils.MoneyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@Primary
public class GoalService extends CommonService<Goal> {
    private final GoalRepository goalRepository;
    private final GoalIncomeRepository goalIncomeRepository;

    @Autowired
    public GoalService(
            @Lazy UserService userService,
            BalanceService balanceService,
            GoalRepository goalRepository,
            GoalIncomeRepository goalIncomeRepository
    ) {
        super(userService, balanceService);
        this.goalRepository = goalRepository;
        this.goalIncomeRepository = goalIncomeRepository;
    }

    public Goal save(Goal goal, Principal principal, Currency currency) {
        return saveWithBalanceUpdate(goal, principal, currency, goalRepository::save);
    }

    public List<Goal> findAll(Principal principal, Currency currency) {
        return findAll(principal, currency, goalRepository::findAllByBalance);
    }

    public List<Goal> findAll(Balance balance) {
        return goalRepository.findAllByBalance(balance);
    }

    public Goal findById(long id, Principal principal) {
        return findById(id, principal, goalRepository::findById);
    }

    public Goal update(long id, Goal newGoal, Principal principal) {
        return update(id, principal, goalRepository::findById, goal -> goal.patch(newGoal), goalRepository::save);
    }

    public String delete(long id, Principal principal) {
        return delete(id, principal, goalRepository::findById, goalRepository::deleteById, true);
    }

    public GoalIncome saveGoalIncome(GoalIncome goalIncome, Principal principal, long id) {
        Goal goal = getGoal(principal, id);
        Balance balance = goal.getBalance();
        if (MoneyCalculator.compare(balance.getValue(), goalIncome.getValue()) < 0) {
            throw new NotEnoughMoneyException(balance.getId());
        }
        goalIncome.setGoal(goal);
        goal.setValue(MoneyCalculator.add(goal.getValue(), goalIncome.getValue()));
        balance.setValue(MoneyCalculator.sub(balance.getValue(), goalIncome.getValue()));
        return goalIncomeRepository.save(goalIncome);
    }

    public List<GoalIncome> findAllGoalIncomes(Principal principal, long id) {
        Goal goal = getGoal(principal, id);
        return goalIncomeRepository.findByGoal(goal);
    }

    public GoalIncome findGoalIncomeById(Principal principal, long goalId, long incomeId) {
        Goal goal = getGoal(principal, goalId);
        return getGoalIncome(goalId, incomeId, goal);
    }

    public GoalIncome updateGoalIncome(long goalId, long incomeId, GoalIncomeDto goalIncomeDto, Principal principal) {
        Goal goal = getGoal(principal, goalId);
        GoalIncome goalIncome = getGoalIncome(goalId, incomeId, goal);
        String oldValue = goalIncome.getValue();
        Balance balance = goal.getBalance();
        if (MoneyCalculator.compare(MoneyCalculator.add(balance.getValue(), oldValue), goalIncome.getValue()) < 0) {
            throw new NotEnoughMoneyException(balance.getId());
        }
        goalIncome.patch(goalIncomeDto);
        goal.setValue(MoneyCalculator.sub(MoneyCalculator.add(goalIncome.getValue(), goal.getValue()), oldValue));
        balance.setValue(MoneyCalculator.add(MoneyCalculator.sub(balance.getValue(), goalIncome.getValue()), oldValue));
        return goalIncomeRepository.save(goalIncome);
    }

    public String deleteGoalIncome(long goalId, long incomeId, Principal principal) {
        Goal goal = getGoal(principal, goalId);
        GoalIncome goalIncome = getGoalIncome(goalId, incomeId, goal);
        goal.setValue(MoneyCalculator.sub(goal.getValue(), goalIncome.getValue()));
        Balance balance = goal.getBalance();
        balance.setValue(MoneyCalculator.add(balance.getValue(), goalIncome.getValue()));
        goalIncomeRepository.deleteById(incomeId);
        return "Successfully deleted";
    }

    private Goal getGoal(Principal principal, long id) {
        Goal goal = goalRepository.findById(id).orElse(null);
        if (goal == null || !Objects.equals(goal.getBalance().getUser().getUsername(), principal.getName())) {
            throw new EntityNotFoundException(id);
        }
        return goal;
    }

    private GoalIncome getGoalIncome(long goalId, long incomeId, Goal goal) {
        GoalIncome goalIncome = goalIncomeRepository.findByGoalAndId(goal, incomeId);
        if (goalIncome == null) {
            throw new EntityNotFoundException(goalId);
        }
        return goalIncome;
    }
}
