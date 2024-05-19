package com.jk.it_one.services;

import com.jk.it_one.repositories.OperationRepository;
import com.jk.it_one.interfaces.Operation;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Transactional
@Setter
@Service
public class CommonOperationService<T extends Operation<T>> extends CommonService<T> {
    protected OperationRepository<T> operationRepository;

    protected boolean isExpense;

    @Autowired
    public CommonOperationService(@Lazy UserService userService, BalanceService balanceService) {
        super(userService, balanceService);
    }

    public T save(T operation, Principal principal, Currency currency) {
        return save(operation, principal, currency, "0");
    }

    public T save(T operation, Principal principal, Currency currency, String oldValue) {
        return saveWithBalanceUpdate(operation, principal, currency, oldValue, operationRepository::save, isExpense);
    }

    public T save(T operation, Balance balance) {
        return save(operation, balance, "0");
    }

    public T save(T operation, Balance balance, String oldValue) {
        return saveWithBalanceUpdate(operation, balance, oldValue, operationRepository::save, isExpense);
    }

    public List<T> findAll(Principal principal, Currency currency) {
        return findAll(principal, currency, operationRepository::findAllByBalanceOrderByDateDesc);
    }

    public T findById(long id, Principal principal) {
        return findById(id, principal, operationRepository::findById);
    }

    public T update(long id, T operation, Principal principal) {
        return update(id, principal, operationRepository::findById, oldOperation -> oldOperation.patch(operation), operationRepository::save, isExpense);
    }

    public String delete(long id, Principal principal) {
        return delete(id, principal, operationRepository::findById, operationRepository::deleteById, isExpense);
    }

    public List<T> findLast3(Balance balance) {
        return operationRepository.findTop3ByBalanceOrderByDateDesc(balance);
    }
}
