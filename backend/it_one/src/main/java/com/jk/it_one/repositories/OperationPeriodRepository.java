package com.jk.it_one.repositories;

import com.jk.it_one.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OperationPeriodRepository<T> extends JpaRepository<T, Long> {
    List<T> findAllByBalanceOrderByStartDayDesc(Balance balance);
}
