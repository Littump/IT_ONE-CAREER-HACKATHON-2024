package com.jk.it_one.services;

import com.jk.it_one.TestsUtils;
import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.*;
import com.jk.it_one.responce_dtos.OperationDto;
import com.jk.it_one.responce_dtos.OperationPeriodDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OperationsServiceTest {
    private AutoCloseable closeable;

    private OperationsService operationsService;

    @Mock
    private IncomeService incomeService;

    @Mock
    private ExpenseService expenseService;

    @Mock
    private IncomePeriodService incomePeriodService;

    @Mock
    private ExpensePeriodService expensePeriodService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        operationsService = new OperationsService(incomeService, expenseService, incomePeriodService, expensePeriodService);
    }

    @AfterEach
    void destroy() throws Exception {
        closeable.close();
    }

    @Test
    void testFindOperations() {
        Principal principal = mock(Principal.class);
        Currency currency = Currency.USD;
        List<Income> incomes = Collections.singletonList(TestsUtils.baseIncome());
        List<Expense> expenses = Collections.singletonList(TestsUtils.baseExpense());

        when(incomeService.findAll(principal, currency)).thenReturn(incomes);
        when(expenseService.findAll(principal, currency)).thenReturn(expenses);

        List<OperationDto> operations = operationsService.findOperations(principal, currency);

        assertEquals(2, operations.size());
    }

    @Test
    void testFindOperationPeriods() {
        Principal principal = mock(Principal.class);
        Currency currency = Currency.EUR;

        List<IncomePeriod> incomePeriods = List.of(TestsUtils.baseIncomePeriod(), TestsUtils.baseIncomePeriod());
        List<ExpensePeriod> expensePeriods = List.of(TestsUtils.baseExpensePeriod(), TestsUtils.baseExpensePeriod());

        when(incomePeriodService.findAll(principal, currency)).thenReturn(incomePeriods);
        when(expensePeriodService.findAll(principal, currency)).thenReturn(expensePeriods);

        List<OperationPeriodDto> operationPeriods = operationsService.findOperationPeriods(principal, currency);

        assertEquals(4, operationPeriods.size());
    }

    @Test
    void testFindLast3() {
        Balance balance = new Balance();
        List<Income> incomes = Collections.nCopies(3, TestsUtils.baseIncome());
        List<Expense> expenses = Collections.nCopies(3, TestsUtils.baseExpense());

        when(incomeService.findLast3(balance)).thenReturn(incomes);
        when(expenseService.findLast3(balance)).thenReturn(expenses);

        List<OperationDto> last3Operations = operationsService.findLast3(balance);

        assertEquals(3, last3Operations.size());
    }
}