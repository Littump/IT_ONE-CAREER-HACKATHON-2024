package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.IncomeRepository;
import com.jk.it_one.requestDtos.IncomeDto;
import com.jk.it_one.utils.MoneyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.security.Principal;
import java.util.Objects;

@Transactional
@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserService userService;
    private final BalanceService balanceService;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository, UserService userService, BalanceService balanceService) {
        this.incomeRepository = incomeRepository;
        this.userService = userService;
        this.balanceService = balanceService;
    }

    public Income save(Income income, Principal principal, Currency currency, String oldValue) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        if (balance == null) {
            balance = new Balance(user, income.getValue(), currency);
        } else {
            balance.setBalance(MoneyCalculator.sub(MoneyCalculator.add(balance.getBalance(), income.getValue()), oldValue));
        }
        income.setBalance(balance);
        return incomeRepository.save(income);
    }

    public List<Income> findAll(Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        return incomeRepository.findAllByBalance(balance);
    }

    public Income findById(long id, Principal principal) {
        User user = userService.findMe(principal);
        Income income = incomeRepository.findById(id).orElse(null);
        if (income != null) {
            if (!Objects.equals(income.getBalance().getUser().getId(), user.getId())) {
                return null;
            }
        }
        return income;
    }
    
    public Income update(long id, IncomeDto incomeDto, Principal principal) {
        Income income = findById(id, principal);
        if (income == null) {
            return null;
        }
        String oldValue = income.getValue();
        income.patch(incomeDto);
        return save(income, principal, income.getBalance().getCurrency(), oldValue);
    }

    public ResponseEntity<?> delete(long id, Principal principal) {
        User user = userService.findMe(principal);
        Income income = incomeRepository.findById(id).orElse(null);
        if (income == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income with this id does not exist or not yours");
        }
        if (!Objects.equals(income.getBalance().getUser().getId(), user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Income with this id does not exist or not yours");
        }
        incomeRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
