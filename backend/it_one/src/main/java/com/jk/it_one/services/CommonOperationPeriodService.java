package com.jk.it_one.services;

import com.jk.it_one.repositories.OperationPeriodRepository;
import com.jk.it_one.interfaces.Operation;
import com.jk.it_one.interfaces.PeriodOperation;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.utils.DataTimeUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Transactional
@Service
@Setter
public class CommonOperationPeriodService<V extends Operation<V>, T extends PeriodOperation> {
    private final UserService userService;
    private final BalanceService balanceService;
    protected OperationPeriodRepository<T> operationPeriodRepository;
    protected CommonOperationService<V> operationService;
    protected Function<T, V> operationConstructor;
    protected boolean isExpense;

    @Autowired
    public CommonOperationPeriodService(@Lazy UserService userService, BalanceService balanceService) {
        this.userService = userService;
        this.balanceService = balanceService;
    }


    public T save(T operationPeriod, Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        if (DataTimeUtils.isCurrentDay(operationPeriod.getStartDay())) {
            V generatedIncome = operationConstructor.apply(operationPeriod);
            operationService.save(generatedIncome, balance);
        }
        operationPeriod.setBalance(balance);
        return operationPeriodRepository.save(operationPeriod);
    }

    public List<T> findAll(Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        return operationPeriodRepository.findAllByBalanceOrderByStartDayDesc(balance);
    }

    public T findById(long id, Principal principal) {
        return getOperationPeriod(id, principal);
    }

    public String delete(long id, Principal principal) {
        getOperationPeriod(id, principal);
        operationPeriodRepository.deleteById(id);
        return "Successfully deleted";
    }

    private T getOperationPeriod(long id, Principal principal) {
        T operationPeriod = operationPeriodRepository.findById(id).orElse(null);
        if (operationPeriod == null
                || !Objects.equals(operationPeriod.getBalance().getUser().getUsername(), principal.getName())) {
            throw new EntityNotFoundException(id);
        }
        return operationPeriod;
    }
}
