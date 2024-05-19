package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Expense;
import com.jk.it_one.request_dtos.ExpenseDto;
import com.jk.it_one.request_dtos.ExpensePatchDto;
import com.jk.it_one.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/expenses")
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    Expense addExpense(@Valid @RequestBody ExpenseDto expenseDto, Principal principal, @RequestParam Currency currency) {
        Expense expense = new Expense(expenseDto);
        return expenseService.save(expense, principal, currency);
    }

    @GetMapping
    List<Expense> getExpenses(Principal principal, @RequestParam Currency currency) {
        return expenseService.findAll(principal, currency);
    }

    @GetMapping("/{id}")
    Expense getExpense(Principal principal, @PathVariable("id") long id) {
        return expenseService.findById(id, principal);
    }

    @PatchMapping("/{id}")
    Expense patchExpense(@Valid @RequestBody ExpensePatchDto expenseDto, Principal principal, @PathVariable("id") long id) {
        Expense expense = new Expense(expenseDto);
        return expenseService.update(id, expense, principal);
    }

    @DeleteMapping("/{id}")
    String deleteExpense(Principal principal, @PathVariable("id") long id) {
        return expenseService.delete(id, principal);
    }
}
