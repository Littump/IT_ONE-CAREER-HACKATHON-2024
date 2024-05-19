package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class BalanceServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    private User user;
    private Principal principal;
    private Currency currency;
    private Balance balance;

    @BeforeEach
    void setUp() {
        user = new User();
        principal = mock(Principal.class);
        currency = Currency.USD;
        balance = new Balance(user, "100", currency);
    }

    @Test
    void save_ShouldReturnSavedBalance() {
        when(balanceRepository.save(any(Balance.class))).thenReturn(balance);

        Balance savedBalance = balanceService.save(balance);

        assertNotNull(savedBalance);
        assertEquals(balance, savedBalance);
        verify(balanceRepository, times(1)).save(balance);
    }

    @Test
    void findUserBalance_ShouldReturnExistingBalance() {
        when(balanceRepository.findByUserAndCurrency(user, currency)).thenReturn(balance);

        Balance foundBalance = balanceService.findUserBalance(user, currency);

        assertNotNull(foundBalance);
        assertEquals(balance, foundBalance);
        verify(balanceRepository, times(1)).findByUserAndCurrency(user, currency);
    }

    @Test
    void findUserBalance_ShouldCreateAndReturnNewBalanceIfNotFound() {
        when(balanceRepository.findByUserAndCurrency(user, currency)).thenReturn(null);

        Balance newBalance = balanceService.findUserBalance(user, currency);

        assertNotNull(newBalance);
        assertEquals("0", newBalance.getValue());
        verify(balanceRepository, times(1)).findByUserAndCurrency(user, currency);
        verify(balanceRepository, times(1)).save(any(Balance.class));
    }

    @Test
    void findUserBalance_ByPrincipal_ShouldReturnBalance() {
        when(userService.findMe(principal)).thenReturn(user);
        when(balanceRepository.findByUserAndCurrency(user, currency)).thenReturn(balance);

        Balance foundBalance = balanceService.findUserBalance(principal, currency);

        assertNotNull(foundBalance);
        assertEquals(balance, foundBalance);
        verify(userService, times(1)).findMe(principal);
        verify(balanceRepository, times(1)).findByUserAndCurrency(user, currency);
    }

    @Test
    void findUserBalances_ShouldReturnUserBalances() {
        List<Balance> balances = Arrays.asList(balance, new Balance(user, "200", currency));
        when(balanceRepository.findBalancesByUser(user)).thenReturn(balances);

        List<Balance> userBalances = balanceService.findUserBalances(user);

        assertNotNull(userBalances);
        assertEquals(2, userBalances.size());
        verify(balanceRepository, times(1)).findBalancesByUser(user);
    }
}
