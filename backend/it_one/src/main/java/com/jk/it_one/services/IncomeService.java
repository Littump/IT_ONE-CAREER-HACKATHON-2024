package com.jk.it_one.services;

import com.jk.it_one.models.Income;
import com.jk.it_one.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class IncomeService extends CommonOperationService<Income> {

    @Autowired
    public IncomeService(@Lazy UserService userService, IncomeRepository incomeRepository, BalanceService balanceService) {
        super(userService, balanceService);
        this.operationRepository = incomeRepository;
        this.isExpense = false;
    }
}
