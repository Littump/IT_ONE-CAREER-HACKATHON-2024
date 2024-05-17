package com.jk.it_one.services;

import com.jk.it_one.models.*;
import com.jk.it_one.repositories.ExpensePeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class ExpensePeriodService extends OperationPeriodService<Expense, ExpensePeriod> {
    @Autowired
    public ExpensePeriodService(ExpensePeriodRepository expensePeriodRepository, ExpenseService expenseService, UserService userService, BalanceService balanceService) {
        super(userService, balanceService);
        this.operationPeriodRepository = expensePeriodRepository;
        this.operationService = expenseService;
        this.operationConstructor = Expense::new;
        this.isExpense = true;
    }
}
