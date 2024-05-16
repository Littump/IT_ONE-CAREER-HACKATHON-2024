package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Income;
import com.jk.it_one.requestDtos.IncomeDto;
import com.jk.it_one.services.IncomeService;
import com.jk.it_one.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class IncomeController {
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/incomes")
    ResponseEntity<?> addIncome(@RequestBody IncomeDto incomeDto, Principal principal, @RequestParam Currency currency) {
        Income income = new Income(incomeDto);
        return ResponseEntity.ok(incomeService.save(income, principal, currency, "0"));
    }

    @GetMapping("/incomes")
    ResponseEntity<?> getIncomes(Principal principal, @RequestParam Currency currency) {
        return ResponseEntity.ok(incomeService.findAll(principal, currency));
    }

    @GetMapping("/incomes/{id}")
    ResponseEntity<?> getIncome(Principal principal, @PathVariable("id") long id) {
        Income income = incomeService.findById(id, principal);
        return income == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income with this id does not exist or not yours")
                : ResponseEntity.ok(income);
    }

    @PatchMapping("/incomes/{id}")
    ResponseEntity<?> patchIncome(@RequestBody IncomeDto incomeDto, Principal principal, @PathVariable("id") long id) {
        Income income = incomeService.update(id, incomeDto, principal);
        return income == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income with this id does not exist or not yours")
                : ResponseEntity.ok(income);
    }

    @DeleteMapping("/incomes/{id}")
    ResponseEntity<?> deleteIncome(Principal principal, @PathVariable("id") long id) {
        return incomeService.delete(id, principal);
    }
}
