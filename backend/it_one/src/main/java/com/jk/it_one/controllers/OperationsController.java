package com.jk.it_one.controllers;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.responceDtos.OperationDto;
import com.jk.it_one.responceDtos.OperationPeriodDto;
import com.jk.it_one.services.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class OperationsController {
    private final OperationsService operationService;

    @Autowired
    public OperationsController(OperationsService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/operations")
    List<OperationDto> getOperations(Principal principal, @RequestParam Currency currency) {
        return operationService.findOperations(principal, currency);
    }

    @GetMapping("/operations_period")
    List<OperationPeriodDto> getOperationsPeriod(Principal principal, @RequestParam Currency currency) {
        return operationService.findOperationPeriods(principal, currency);
    }
}
