package com.jk.it_one.services;

import com.jk.it_one.models.Expense;
import com.jk.it_one.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Primary
public class ExpenseService extends OperationService<Expense>  {
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserService userService, BalanceService balanceService) {
        super(userService, balanceService);
        this.operationRepository = expenseRepository;
        this.isExpense = true;
    }
}
