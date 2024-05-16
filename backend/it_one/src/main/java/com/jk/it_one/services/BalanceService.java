package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.BalanceRepository;
import com.jk.it_one.repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BalanceService {
    private final IncomeRepository incomeRepository;
    private final UserService userService;
    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceService(IncomeRepository incomeRepository, @Lazy UserService userService, BalanceRepository balanceRepository) {
        this.incomeRepository = incomeRepository;
        this.userService = userService;
        this.balanceRepository = balanceRepository;
    }

    public Balance save(Balance balance, Principal principal) {
        User me = userService.findMe(principal);
        balance.setUser(me);
        return balanceRepository.save(balance);
    }

    public Balance findUserBalance(User user, Currency currency) {
        return balanceRepository.findByUserAndCurrency(user, currency);
    }

    public List<Balance> findUserBalances(User user) {
        return balanceRepository.findBalancesByUser(user);
    }
}
