package com.jk.it_one.repositories;

import com.jk.it_one.models.Expense;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends OperationRepository<Expense> {
}
