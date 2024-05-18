package com.jk.it_one.repositories;

import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Expense;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExpenseRepository extends OperationRepository<Expense> {
}
