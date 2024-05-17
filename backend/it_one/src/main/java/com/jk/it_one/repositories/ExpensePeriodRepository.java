package com.jk.it_one.repositories;

import com.jk.it_one.Interfaces.OperationRepository;
import com.jk.it_one.models.ExpensePeriod;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensePeriodRepository extends OperationRepository<ExpensePeriod> {
}
