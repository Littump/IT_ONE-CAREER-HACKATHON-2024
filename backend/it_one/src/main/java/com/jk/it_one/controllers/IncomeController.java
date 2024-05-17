package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Income;
import com.jk.it_one.requestDtos.IncomeDto;
import com.jk.it_one.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class IncomeController {
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/incomes")
    Income addIncome(@RequestBody IncomeDto incomeDto, Principal principal, @RequestParam Currency currency) {
        return incomeService.save(incomeDto, principal, currency);
    }

    @GetMapping("/incomes")
    List<Income> getIncomes(Principal principal, @RequestParam Currency currency) {
        return incomeService.findAll(principal, currency);
    }

    @GetMapping("/incomes/{id}")
    Income getIncome(Principal principal, @PathVariable("id") long id) {
        return incomeService.findById(id, principal);
    }

    @PatchMapping("/incomes/{id}")
    Income patchIncome(@RequestBody IncomeDto incomeDto, Principal principal, @PathVariable("id") long id) {
        return incomeService.update(id, incomeDto, principal);
    }

    @DeleteMapping("/incomes/{id}")
    String deleteIncome(Principal principal, @PathVariable("id") long id) {
        return incomeService.delete(id, principal);
    }
}
