package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.ExpensePeriod;
import com.jk.it_one.request_dtos.ExpensePeriodDto;
import com.jk.it_one.services.ExpensePeriodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/expenses_period")
@RestController
public class ExpensePeriodController {
    private final ExpensePeriodService expensePeriodService;

    @Autowired
    public ExpensePeriodController(ExpensePeriodService expensePeriodService) {
        this.expensePeriodService = expensePeriodService;
    }

    @PostMapping
    public ExpensePeriod addExpensePeriod(@Valid @RequestBody ExpensePeriodDto expensePeriodDto, Principal principal, @RequestParam Currency currency) {
        ExpensePeriod expensePeriod = new ExpensePeriod(expensePeriodDto);
        return expensePeriodService.save(expensePeriod, principal, currency);
    }

    @GetMapping
    public List<ExpensePeriod> getExpensesPeriod(Principal principal, @RequestParam Currency currency) {
        return expensePeriodService.findAll(principal, currency);
    }

    @GetMapping("/{id}")
    public ExpensePeriod getExpensePeriod(Principal principal, @PathVariable("id") long id) {
        return expensePeriodService.findById(id, principal);
    }

    @DeleteMapping("/{id}")
    public String deleteExpensePeriod(Principal principal, @PathVariable("id") long id) {
        return expensePeriodService.delete(id, principal);
    }
}
