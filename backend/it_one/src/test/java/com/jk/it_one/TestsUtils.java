package com.jk.it_one;

import com.jk.it_one.enums.ExpenseKind;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.enums.Period;
import com.jk.it_one.models.Expense;
import com.jk.it_one.models.ExpensePeriod;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.IncomePeriod;

import java.util.Date;

public class TestsUtils {
    public static Income baseIncome() {
        Income income = new Income();
        income.setId(1L);
        income.setValue("239");
        income.setKind(IncomeKind.GIFTS);
        income.setDate(new Date());
        return income;
    }

    public static Expense baseExpense() {
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setValue("239");
        expense.setKind(ExpenseKind.EDUCATION);
        expense.setDate(new Date());
        return expense;
    }

    public static IncomePeriod baseIncomePeriod() {
        IncomePeriod incomePeriod = new IncomePeriod();
        incomePeriod.setId(1L);
        incomePeriod.setValue("239");
        incomePeriod.setKind(IncomeKind.GIFTS);
        incomePeriod.setPeriodValue(10);
        incomePeriod.setPeriodKind(Period.DAY);
        incomePeriod.setStartDay(new Date());
        return incomePeriod;
    }

    public static ExpensePeriod baseExpensePeriod() {
        ExpensePeriod expensePeriod = new ExpensePeriod();
        expensePeriod.setId(1L);
        expensePeriod.setValue("239");
        expensePeriod.setKind(ExpenseKind.EDUCATION);
        expensePeriod.setPeriodValue(10);
        expensePeriod.setPeriodKind(Period.DAY);
        expensePeriod.setStartDay(new Date());
        return expensePeriod;
    }
}
