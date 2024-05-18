package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Goal;
import com.jk.it_one.models.GoalIncome;
import com.jk.it_one.requestDtos.GoalDto;
import com.jk.it_one.requestDtos.GoalIncomeDto;
import com.jk.it_one.requestDtos.GoalPatchDto;
import com.jk.it_one.services.GoalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/goals")
@RestController
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    Goal addGoal(@Valid @RequestBody GoalDto goalDto, Principal principal, @RequestParam Currency currency) {
        Goal goal = new Goal(goalDto);
        return goalService.save(goal, principal, currency);
    }

    @GetMapping
    List<Goal> getGoals(Principal principal, @RequestParam Currency currency) {
        return goalService.findAll(principal, currency);
    }

    @GetMapping("/{id}")
    Goal getGaol(Principal principal, @PathVariable("id") long id) {
        return goalService.findById(id, principal);
    }

    @PatchMapping("/{id}")
    Goal patchGoal(@Valid @RequestBody GoalPatchDto goalDto, Principal principal, @PathVariable("id") long id) {
        Goal goal = new Goal(goalDto);
        return goalService.update(id, goal, principal);
    }

    @DeleteMapping("/{id}")
    String deleteGoal(Principal principal, @PathVariable("id") long id) {
        return goalService.delete(id, principal);
    }

    @PostMapping("/{id}/incomes")
    GoalIncome addGoalIncome(@Valid @RequestBody GoalIncomeDto goalIncomeDto, Principal principal, @PathVariable("id") long id) {
        GoalIncome goal = new GoalIncome(goalIncomeDto);
        return goalService.saveGoalIncome(goal, principal, id);
    }

    @GetMapping("/{id}/incomes")
    List<GoalIncome> getGoalIncomes(Principal principal, @PathVariable("id") long id) {
        return goalService.findAllGoalIncomes(principal, id);
    }

    @GetMapping("/{goalId}/incomes/{goalIncomeId}")
    GoalIncome getGoalIncome(Principal principal, @PathVariable("goalId") long goalId, @PathVariable("goalIncomeId") long goalIncomeId) {
        return goalService.findGoalIncomeById(principal, goalId, goalIncomeId);
    }

    @PatchMapping("/{goalId}/incomes/{goalIncomeId}")
    GoalIncome patchGoalIncome(@Valid @RequestBody GoalIncomeDto goalIncomeDto, Principal principal, @PathVariable("goalId") long goalId, @PathVariable("goalIncomeId") long goalIncomeId) {
        return goalService.updateGoalIncome(goalId, goalIncomeId, goalIncomeDto, principal);
    }

    @DeleteMapping("/{goalId}/incomes/{goalIncomeId}")
    String deleteGoalIncome(Principal principal, @PathVariable("goalId") long goalId, @PathVariable("goalIncomeId") long goalIncomeId) {
        return goalService.deleteGoalIncome(goalId, goalIncomeId, principal);
    }
}
