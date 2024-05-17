package com.jk.it_one.Interfaces;

import com.jk.it_one.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OperationRepository<T> extends JpaRepository<T, Long> {
    List<T> findAllByBalance(Balance balance);
}
