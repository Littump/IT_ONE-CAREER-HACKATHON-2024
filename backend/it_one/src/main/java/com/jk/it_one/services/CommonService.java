package com.jk.it_one.services;

import com.jk.it_one.Interfaces.WithBalanceAndValue;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.utils.MoneyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class CommonService {
    protected final UserService userService;
    protected final BalanceService balanceService;

    @Autowired
    public CommonService(UserService userService, BalanceService balanceService) {
        this.userService = userService;
        this.balanceService = balanceService;
    }

    //balance.v + ent.val - old.v
    protected <T extends WithBalanceAndValue> T saveWithBalanceUpdate(
            T entity,
            Balance balance,
            User user,
            Currency currency,
            String oldValue,
            Function<T, T> saver
    ) {
        if (balance == null) {
            balance = new Balance(user, "0", currency);
        }
        balance.setValue(MoneyCalculator.sub(MoneyCalculator.add(balance.getValue(), entity.getValue()), oldValue));
        entity.setBalance(balance);
        return saver.apply(entity);
    }

    protected <T extends WithBalanceAndValue> T saveWithBalanceUpdate(
            T entity,
            Principal principal,
            Currency currency,
            String oldValue,
            Function<T, T> saver
    ) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        return saveWithBalanceUpdate(entity, balance, user, currency, oldValue, saver);
    }

    protected <T extends WithBalanceAndValue> List<T> findAll(
            Principal principal,
            Currency currency,
            Function<Balance, List<T>> saver
    ) {
        Balance balance = balanceService.findUserBalance(principal, currency);
        return saver.apply(balance);
    }

    protected <T extends WithBalanceAndValue> T findById(
            long id,
            Principal principal,
            Function<Long, Optional<T>> finder
    ) {
        User user = userService.findMe(principal);
        return findById(id, user, finder);
    }

    protected <T extends WithBalanceAndValue> T findById(
            long id,
            User user,
            Function<Long, Optional<T>> finder
    ) {
        T entity = finder.apply(id).orElse(null);
        if (entity == null || !Objects.equals(entity.getBalance().getUser().getId(), user.getId())) {
            throw new EntityNotFoundException(id);
        }
        return entity;
    }

    protected <T extends WithBalanceAndValue> T update(
            long id,
            Principal principal,
            Function<Long, Optional<T>> finder,
            Consumer<T> patcher,
            Function<T, T> saver
    ) {
        User user = userService.findMe(principal);
        T entity = findById(id, user, finder);
        String oldValue = entity.getBalance().getValue();
        patcher.accept(entity);
        Balance balance = entity.getBalance();
        saveWithBalanceUpdate(entity, entity.getBalance(), user, balance.getCurrency(), oldValue, saver);
        return entity;
    }

    protected <T extends WithBalanceAndValue> String delete(
            long id,
            Principal principal,
            Function<Long, Optional<T>> finder,
            Consumer<Long> deleter,
            BiFunction<String, String, String> moneyResolver
    ) {
        User user = userService.findMe(principal);
        T entity = findById(id, user, finder);
        if (!Objects.equals(entity.getBalance().getUser().getId(), user.getId())) {
            throw new EntityNotFoundException(id);
        }
        Balance balance = entity.getBalance();
        balance.setValue(moneyResolver.apply(balance.getValue(), entity.getValue()));
        balanceService.save(balance);
        deleter.accept(id);
        return "Successfully deleted";
    }

}
