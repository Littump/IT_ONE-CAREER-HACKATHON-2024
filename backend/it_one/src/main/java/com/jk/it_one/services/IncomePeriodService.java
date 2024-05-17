package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.IncomePeriod;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.IncomePeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class IncomePeriodService {
    private final IncomePeriodRepository incomePeriodRepository;
    private final IncomeService incomeService;
    private final UserService userService;
    private final BalanceService balanceService;

    @Autowired
    public IncomePeriodService(IncomePeriodRepository incomePeriodRepository, IncomeService incomeService, UserService userService, BalanceService balanceService) {
        this.incomePeriodRepository = incomePeriodRepository;
        this.incomeService = incomeService;
        this.userService = userService;
        this.balanceService = balanceService;
    }

    public IncomePeriod save(IncomePeriod incomePeriod, Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        if (isCurrentDay(incomePeriod.getStartDay())) {
            Income generatedIncome = new Income(incomePeriod);
            incomeService.save(generatedIncome, balance, user, currency);
        }
        incomePeriod.setBalance(balance);
        return incomePeriodRepository.save(incomePeriod);
    }

    public List<IncomePeriod> findAll(Principal principal, Currency currency) {
        User user = userService.findMe(principal);
        Balance balance = balanceService.findUserBalance(user, currency);
        return incomePeriodRepository.findAllByBalance(balance);
    }

    public IncomePeriod findById(long id, Principal principal) {
        return getIncomePeriod(id, principal);
    }

    public String delete(long id, Principal principal) {
        getIncomePeriod(id, principal);
        incomePeriodRepository.deleteById(id);
        return "Successfully deleted";
    }

    private IncomePeriod getIncomePeriod(long id, Principal principal) {
        IncomePeriod incomePeriod = incomePeriodRepository.findById(id).orElse(null);
        if (incomePeriod == null || !Objects.equals(incomePeriod.getBalance().getUser().getUsername(), principal.getName())) {
            throw new EntityNotFoundException(id);
        }
        return incomePeriod;
    }

    private boolean isCurrentDay(Date startDay) {
        Calendar startDayCalendar = Calendar.getInstance();
        Calendar currentDayCalendar = Calendar.getInstance();
        startDayCalendar.setTime(startDay);
        currentDayCalendar.setTime(new Date());
        return startDayCalendar.get(Calendar.YEAR) == currentDayCalendar.get(Calendar.YEAR) &&
                startDayCalendar.get(Calendar.DAY_OF_YEAR) == currentDayCalendar.get(Calendar.DAY_OF_YEAR);
    }
}
