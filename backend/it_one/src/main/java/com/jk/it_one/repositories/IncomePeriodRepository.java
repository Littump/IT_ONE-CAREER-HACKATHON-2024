package com.jk.it_one.repositories;

import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.IncomePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomePeriodRepository extends JpaRepository<IncomePeriod, Long> {
    List<IncomePeriod> findAllByBalance(Balance balance);
}
