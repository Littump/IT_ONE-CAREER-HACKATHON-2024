package com.jk.it_one.services;

import com.jk.it_one.models.Income;
import com.jk.it_one.models.IncomePeriod;
import com.jk.it_one.repositories.IncomePeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class IncomePeriodService extends CommonOperationPeriodService<Income, IncomePeriod> {

    @Autowired
    public IncomePeriodService(
            @Lazy UserService userService,
            IncomePeriodRepository incomePeriodRepository,
            IncomeService incomeService,
            BalanceService balanceService
    ) {
        super(userService, balanceService);
        this.operationPeriodRepository = incomePeriodRepository;
        this.operationService = incomeService;
        this.operationConstructor = Income::new;
        this.isExpense = false;
    }
}
