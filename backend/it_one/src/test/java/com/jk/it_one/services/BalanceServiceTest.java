package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class BalanceServiceTest {
    private final BalanceService balanceService;

    @Autowired
    public BalanceServiceTest(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public User getTestUser() {
        User user = new User();
        user.setUsername("test");
        user.setName("test");
        user.setPassword("password");
        return user;
    }

    public Balance getTestBalance(User user) {
        Balance balance = new Balance();
        balance.setValue("10");
        balance.setUser(user);
        balance.setCurrency(Currency.USD);
        return balance;
    }

    @Test
    public void testSave() {
        User user = getTestUser();
        Balance balance = getTestBalance(user);
        balanceService.save(balance);

        Balance balance2 = balanceService.findUserBalance(user, Currency.USD);
        assert balance2.getValue().equals("10");
    }

    @Test
    public void testFindUserBalance() {
        User user = getTestUser();
        Balance balance = balanceService.findUserBalance(user, Currency.USD);
        assert balance.getValue().equals("0");
    }

    @Test
    public void testFindUserBalances() {
        User user = getTestUser();
        Balance balance = getTestBalance(user);
        balanceService.save(balance);

        List<Balance> balances = balanceService.findUserBalances(user);
        assert balances.size() == 1;
    }
}
