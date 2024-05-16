package com.jk.it_one.repositories;

import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findAllByBalance(Balance balance);
    Income findByBalanceAndId(Balance balance, Long id);
}
