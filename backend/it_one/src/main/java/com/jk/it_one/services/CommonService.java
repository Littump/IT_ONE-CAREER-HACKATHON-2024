package com.jk.it_one.services;

import com.jk.it_one.interfaces.Operation;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.exceptions.NotEnoughMoneyException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.utils.MoneyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

@Service
public class CommonService<T extends Operation<T>> {
    protected final UserService userService;
    protected final BalanceService balanceService;

    @Autowired
    public CommonService(@Lazy UserService userService, BalanceService balanceService) {
        this.userService = userService;
        this.balanceService = balanceService;
    }

    protected T saveWithBalanceUpdate(
            T entity,
            Balance balance,
            String oldValue,
            UnaryOperator<T> saver,
            boolean reversed
    ) {
        balance.setValue(reversed
                ? (MoneyCalculator.add(MoneyCalculator.sub(balance.getValue(), entity.getValue()), oldValue))
                : MoneyCalculator.sub(MoneyCalculator.add(balance.getValue(), entity.getValue()), oldValue));
        if (MoneyCalculator.compare(balance.getValue(), "0") < 0) {
            throw new NotEnoughMoneyException(balance.getId());
        }
        entity.setBalance(balance);
        return saver.apply(entity);
    }

    protected T saveWithBalanceUpdate(
            T entity,
            Principal principal,
            Currency currency,
            UnaryOperator<T> saver
    ) {
        return saveWithBalanceUpdate(entity, principal, currency, "0", saver, false);
    }

    protected T saveWithBalanceUpdate(
            T entity,
            Principal principal,
            Currency currency,
            String oldValue,
            UnaryOperator<T> saver,
            boolean reversed
    ) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        return saveWithBalanceUpdate(entity, balance, oldValue, saver, reversed);
    }

    protected List<T> findAll(
            Principal principal,
            Currency currency,
            Function<Balance, List<T>> saver
    ) {
        Balance balance = balanceService.findUserBalance(principal, currency);
        return saver.apply(balance);
    }

    protected List<T> findAll(
            Balance balance,
            Function<Balance, List<T>> saver
    ) {
        return saver.apply(balance);
    }

    protected T findById(
            long id,
            Principal principal,
            LongFunction<Optional<T>> finder
    ) {
        User user = userService.findMe(principal);
        return findById(id, user, finder);
    }

    protected T findById(
            long id,
            User user,
            LongFunction<Optional<T>> finder
    ) {
        T entity = finder.apply(id).orElse(null);
        if (entity == null || !Objects.equals(entity.getBalance().getUser().getId(), user.getId())) {
            throw new EntityNotFoundException(id);
        }
        return entity;
    }

    protected T update(
            long id,
            Principal principal,
            LongFunction<Optional<T>> finder,
            Consumer<T> patcher,
            UnaryOperator<T> saver
    ) {
        return update(id, principal, finder, patcher, saver, false);
    }

    protected T update(
            long id,
            Principal principal,
            LongFunction<Optional<T>> finder,
            Consumer<T> patcher,
            UnaryOperator<T> saver,
            boolean reversed
    ) {
        User user = userService.findMe(principal);
        T entity = findById(id, user, finder);
        String oldValue = entity.getBalance().getValue();
        patcher.accept(entity);
        saveWithBalanceUpdate(entity, entity.getBalance(), oldValue, saver, reversed);
        return entity;
    }

    protected String delete(
            long id,
            Principal principal,
            LongFunction<Optional<T>> finder,
            LongConsumer deleter
    ) {
        return delete(id, principal, finder, deleter, false);
    }

    protected String delete(
            long id,
            Principal principal,
            LongFunction<Optional<T>> finder,
            LongConsumer deleter,
            boolean reversed
    ) {
        User user = userService.findMe(principal);
        T entity = findById(id, user, finder);
        if (!Objects.equals(entity.getBalance().getUser().getId(), user.getId())) {
            throw new EntityNotFoundException(id);
        }
        Balance balance = entity.getBalance();
        balance.setValue(reversed ? MoneyCalculator.add(balance.getValue(), entity.getValue())
                : MoneyCalculator.sub(balance.getValue(), entity.getValue()));
        balanceService.save(balance);
        deleter.accept(id);
        return "Successfully deleted";
    }
}
