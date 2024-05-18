package com.jk.it_one.repositories;

import com.jk.it_one.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OperationRepository<T> extends JpaRepository<T, Long> {
    List<T> findAllByBalanceOrderByDateDesc(Balance balance);
    List<T> findTop3ByBalanceOrderByDateDesc(Balance balance);
}
