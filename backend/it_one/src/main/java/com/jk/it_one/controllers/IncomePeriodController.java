package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.IncomePeriod;
import com.jk.it_one.requestDtos.IncomePeriodDto;
import com.jk.it_one.services.IncomePeriodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api")
@RestController
public class IncomePeriodController {
    private final IncomePeriodService incomePeriodService;

    @Autowired
    public IncomePeriodController(IncomePeriodService incomePeriodService) {
        this.incomePeriodService = incomePeriodService;
    }

    @PostMapping("/incomes_period")
    IncomePeriod addIncomePeriod(@Valid @RequestBody IncomePeriodDto incomePeriodDto, Principal principal, @RequestParam Currency currency) {
        IncomePeriod incomePeriod = new IncomePeriod(incomePeriodDto);
        return incomePeriodService.save(incomePeriod, principal, currency);
    }

    @GetMapping("/incomes_period")
    List<IncomePeriod> getIncomesPeriod(Principal principal, @RequestParam Currency currency) {
        return incomePeriodService.findAll(principal, currency);
    }

    @GetMapping("/incomes_period/{id}")
    IncomePeriod getIncomePeriod(Principal principal, @PathVariable("id") long id) {
        return incomePeriodService.findById(id, principal);
    }

    @DeleteMapping("/incomes_period/{id}")
    String deleteIncomePeriod(Principal principal, @PathVariable("id") long id) {
        return incomePeriodService.delete(id, principal);
    }
}
