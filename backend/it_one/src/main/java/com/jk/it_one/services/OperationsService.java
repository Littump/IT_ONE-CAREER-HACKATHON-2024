package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Expense;
import com.jk.it_one.models.Income;
import com.jk.it_one.responce_dtos.OperationDto;
import com.jk.it_one.responce_dtos.OperationPeriodDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

@Transactional
@Service
public class OperationsService {
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final IncomePeriodService incomePeriodService;
    private final ExpensePeriodService expensePeriodService;

    public OperationsService(
            IncomeService incomeService,
            ExpenseService expenseService,
            IncomePeriodService incomePeriodService,
            ExpensePeriodService expensePeriodService
    ) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.incomePeriodService = incomePeriodService;
        this.expensePeriodService = expensePeriodService;
    }

    public List<OperationDto> findOperations(Principal principal, Currency currency) {
        return findAndConcat(principal, currency, incomeService::findAll, expenseService::findAll,
                OperationDto::new, OperationDto::new, OperationDto::getDate);
    }

    public List<OperationPeriodDto> findOperationPeriods(Principal principal, Currency currency) {
        return findAndConcat(principal, currency, incomePeriodService::findAll, expensePeriodService::findAll,
                OperationPeriodDto::new, OperationPeriodDto::new, OperationPeriodDto::getStartDay);
    }

    public List<OperationDto> findLast3(Balance balance) {
        List<Income> incomes = incomeService.findLast3(balance);
        List<Expense> expenses = expenseService.findLast3(balance);
        return Stream.concat(
                        incomes.stream().map(OperationDto::new),
                        expenses.stream().map(OperationDto::new))
                .sorted(Comparator.comparing(OperationDto::getDate).reversed()).limit(3).toList();
    }

    private static <T, I, E> List<T> findAndConcat(
            Principal principal,
            Currency currency,
            BiFunction<Principal, Currency, List<I>> finder1,
            BiFunction<Principal, Currency, List<E>> finder2,
            Function<I, T> toDtoConvertor1,
            Function<E, T> toDtoConvertor2,
            Function<T, Date> keyExtractor) {
        List<I> incomes = finder1.apply(principal, currency);
        List<E> expenses = finder2.apply(principal, currency);
        return Stream.concat(
                        incomes.stream().map(toDtoConvertor1),
                        expenses.stream().map(toDtoConvertor2))
                .sorted(Comparator.comparing(keyExtractor).reversed()).toList();
    }
}