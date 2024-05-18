package com.jk.it_one.services;

import com.jk.it_one.models.*;
import com.jk.it_one.repositories.ExpensePeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class ExpensePeriodService extends CommonOperationPeriodService<Expense, ExpensePeriod> {
    @Autowired
    public ExpensePeriodService(@Lazy UserService userService, ExpensePeriodRepository expensePeriodRepository, ExpenseService expenseService, BalanceService balanceService) {
        super(userService, balanceService);
        this.operationPeriodRepository = expensePeriodRepository;
        this.operationService = expenseService;
        this.operationConstructor = Expense::new;
        this.isExpense = true;
    }
}
