package com.jk.it_one.repositories;

import com.jk.it_one.models.Goal;
import com.jk.it_one.models.GoalIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalIncomeRepository extends JpaRepository<GoalIncome, Long> {
    List<GoalIncome> findByGoal(Goal goal);
    GoalIncome findByGoalAndId(Goal goal, Long id);
}
