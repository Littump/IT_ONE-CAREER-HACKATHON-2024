package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Transactional(isolation = Isolation.SERIALIZABLE)
@Service
public class BalanceService {
    private final UserService userService;
    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceService(@Lazy UserService userService, BalanceRepository balanceRepository) {
        this.userService = userService;
        this.balanceRepository = balanceRepository;
    }

    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    public Balance findUserBalance(User user, Currency currency) {
        Balance balance = balanceRepository.findByUserAndCurrency(user, currency);
        if (balance == null) {
            balance = new Balance(user, "0", currency);
            save(balance);
        }
        return balance;
    }

    public Balance findUserBalance(Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        return findUserBalance(user, currency);
    }

    public List<Balance> findUserBalances(User user) {
        return balanceRepository.findBalancesByUser(user);
    }
}
