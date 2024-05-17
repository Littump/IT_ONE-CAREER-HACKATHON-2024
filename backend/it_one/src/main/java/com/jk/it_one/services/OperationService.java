package com.jk.it_one.services;

import com.jk.it_one.Interfaces.OperationRepository;
import com.jk.it_one.Interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Transactional
@Setter
@Service
public class OperationService<T extends WithBalanceAndValue<T>> extends CommonService<T>{
    protected OperationRepository<T> operationRepository;
    
    protected boolean isExpense;

    @Autowired
    public OperationService(UserService userService, BalanceService balanceService) {
        super(userService, balanceService);
    }

    public T save(T operation, Principal principal, Currency currency) {
        return save(operation, principal, currency, "0");
    }

    public T save(T operation, Principal principal, Currency currency, String oldValue) {
        return saveWithBalanceUpdate(operation, principal, currency, oldValue, operationRepository::save, isExpense);
    }

    public T save(T operation, Balance balance, User user, Currency currency) {
        return save(operation, balance, user, currency, "0");
    }

    public T save(T operation, Balance balance, User user, Currency currency, String oldValue) {
        return saveWithBalanceUpdate(operation, balance, user, currency, oldValue, operationRepository::save, isExpense);
    }

    public List<T> findAll(Principal principal, Currency currency) {
        return findAll(principal, currency, operationRepository::findAllByBalance);
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
}
