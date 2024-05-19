package com.jk.it_one.repositories;

import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByBalance(Balance balance);
}
