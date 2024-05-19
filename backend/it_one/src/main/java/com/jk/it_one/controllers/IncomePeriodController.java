package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.IncomePeriod;
import com.jk.it_one.request_dtos.IncomePeriodDto;
import com.jk.it_one.services.IncomePeriodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/incomes_period")
@RestController
public class IncomePeriodController {
    private final IncomePeriodService incomePeriodService;

    @Autowired
    public IncomePeriodController(IncomePeriodService incomePeriodService) {
        this.incomePeriodService = incomePeriodService;
    }

    @PostMapping
    public IncomePeriod addIncomePeriod(@Valid @RequestBody IncomePeriodDto incomePeriodDto, Principal principal, @RequestParam Currency currency) {
        IncomePeriod incomePeriod = new IncomePeriod(incomePeriodDto);
        return incomePeriodService.save(incomePeriod, principal, currency);
    }

    @GetMapping
    public List<IncomePeriod> getIncomesPeriod(Principal principal, @RequestParam Currency currency) {
        return incomePeriodService.findAll(principal, currency);
    }

    @GetMapping("/{id}")
    public IncomePeriod getIncomePeriod(Principal principal, @PathVariable("id") long id) {
        return incomePeriodService.findById(id, principal);
    }

    @DeleteMapping("/{id}")
    public String deleteIncomePeriod(Principal principal, @PathVariable("id") long id) {
        return incomePeriodService.delete(id, principal);
    }
}
