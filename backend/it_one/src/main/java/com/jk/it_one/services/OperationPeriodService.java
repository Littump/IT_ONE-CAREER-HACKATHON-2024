package com.jk.it_one.services;

import com.jk.it_one.Interfaces.OperationRepository;
import com.jk.it_one.Interfaces.WithBalanceAndValue;
import com.jk.it_one.Interfaces.WithBalanceValueAndStartDay;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.utils.DataTimeUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Transactional
@Service
@Setter
public class OperationPeriodService<V extends WithBalanceAndValue<V>, T extends WithBalanceValueAndStartDay<T>> {
    private final UserService userService;
    private final BalanceService balanceService;
    protected OperationRepository<T> operationPeriodRepository;
    protected OperationService<V> operationService;
    protected Function<T, V> operationConstructor;
    protected boolean isExpense;

    @Autowired
    public OperationPeriodService(UserService userService, BalanceService balanceService) {
        this.userService = userService;
        this.balanceService = balanceService;
    }


    public T save(T operationPeriod, Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        if (DataTimeUtils.isCurrentDay(operationPeriod.getStartDay())) {
            V generatedIncome = operationConstructor.apply(operationPeriod);
            operationService.save(generatedIncome, balance, user, currency);
        }
        operationPeriod.setBalance(balance);
        return operationPeriodRepository.save(operationPeriod);
    }

    public List<T> findAll(Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        return operationPeriodRepository.findAllByBalance(balance);
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
        if (operationPeriod == null || !Objects.equals(operationPeriod.getBalance().getUser().getUsername(), principal.getName())) {
            throw new EntityNotFoundException(id);
        }
        return operationPeriod;
    }
}
