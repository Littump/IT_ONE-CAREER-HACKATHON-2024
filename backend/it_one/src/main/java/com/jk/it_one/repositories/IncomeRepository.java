package com.jk.it_one.repositories;

import com.jk.it_one.Interfaces.OperationRepository;
import com.jk.it_one.models.Income;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends OperationRepository<Income> {
}
