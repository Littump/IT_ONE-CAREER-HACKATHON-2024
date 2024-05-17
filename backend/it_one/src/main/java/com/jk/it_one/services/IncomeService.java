package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.IncomeRepository;
import com.jk.it_one.requestDtos.IncomeDto;
import com.jk.it_one.utils.MoneyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.security.Principal;

@Transactional
@Service
@Primary
public class IncomeService extends CommonService {
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository, UserService userService, BalanceService balanceService) {
        super(userService, balanceService);
        this.incomeRepository = incomeRepository;
    }

    public Income save(IncomeDto incomeDto, Principal principal, Currency currency) {
        Income income = new Income(incomeDto);
        return save(income, principal, currency, "0");
    }

    public Income save(Income income, Principal principal, Currency currency, String oldValue) {
        return saveWithBalanceUpdate(income, principal, currency, oldValue, incomeRepository::save);
    }

    public Income save(Income income, Balance balance, User user, Currency currency) {
        return save(income, balance, user, currency, "0");
    }

    public Income save(Income income, Balance balance, User user, Currency currency, String oldValue) {
        return saveWithBalanceUpdate(income, balance, user, currency, oldValue, incomeRepository::save);
    }

    public List<Income> findAll(Principal principal, Currency currency) {
        return findAll(principal, currency, incomeRepository::findAllByBalance);
    }

    public Income findById(long id, Principal principal) {
        return findById(id, principal, incomeRepository::findById);
    }

    public Income update(long id, IncomeDto incomeDto, Principal principal) {
        return update(id, principal, incomeRepository::findById, income -> income.patch(incomeDto), incomeRepository::save);
    }

    public String delete(long id, Principal principal) {
        return delete(id, principal, incomeRepository::findById, incomeRepository::deleteById, MoneyCalculator::sub);
    }
}
