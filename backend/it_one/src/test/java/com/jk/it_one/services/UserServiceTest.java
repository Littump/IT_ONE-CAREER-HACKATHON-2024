package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.enums.IncomeKind;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Goal;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.UserRepository;
import com.jk.it_one.responce_dtos.GoalDto;
import com.jk.it_one.responce_dtos.OperationDto;
import com.jk.it_one.responce_dtos.ProfileDto;
import com.jk.it_one.security.UserDetailsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private AutoCloseable closeable;

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BalanceService balanceService;

    @Mock
    private GoalService goalService;

    @Mock
    private OperationsService operationsService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, balanceService, goalService, operationsService);
    }

    @AfterEach
    void destroy() throws Exception {
        closeable.close();
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        UserDetailsImpl userDetails = (UserDetailsImpl) userService.loadUserByUsername("testUser");
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findUserByUsername("nonExistingUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonExistingUser"));
    }

    @Test
    void testSave() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);
        assertEquals(user, savedUser);
    }

    @Test
    void testExistsByUsername() {
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);
        when(userRepository.existsByUsername("nonExistingUser")).thenReturn(false);

        assertTrue(userService.existsByUsername("existingUser"));
        assertFalse(userService.existsByUsername("nonExistingUser"));
    }

    @Test
    void testFindMe_UserFound() {
        User user = new User();
        user.setUsername("testUser");
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");
        when(userRepository.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        User foundUser = userService.findMe(principal);
        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    void testFindMe_UserNotFound() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("nonExistingUser");
        when(userRepository.findUserByUsername("nonExistingUser")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findMe(principal));
    }

    @Test
    void testGetUserProfile() {
        User user = new User();
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("testUser");
        when(userRepository.findUserByUsername("testUser")).thenReturn(Optional.of(user));

        Currency currency = Currency.USD;

        Balance curBalance = new Balance();
        curBalance.setValue("10");
        curBalance.setCurrency(Currency.RUB);
        Balance balance1 = new Balance();
        balance1.setValue("100");
        balance1.setCurrency(Currency.RUB);
        Balance balance2 = new Balance();
        balance2.setValue("100");
        balance2.setCurrency(Currency.RUB);
        List<Balance> balances = List.of(balance1, balance2);
        Income income = new Income();
        income.setId(1L);
        income.setValue("239");
        income.setKind(IncomeKind.GIFTS);

        List<OperationDto> operations = Collections.singletonList(new OperationDto(income));
        List<Goal> goals = Collections.singletonList(new Goal());

        when(balanceService.findUserBalance(user, currency)).thenReturn(curBalance);
        when(balanceService.findUserBalances(user)).thenReturn(balances);
        when(operationsService.findLast3(curBalance)).thenReturn(operations);
        when(goalService.findAll(curBalance)).thenReturn(goals);

        ProfileDto userProfile = userService.getUserProfile(principal, currency);

        assertNotNull(userProfile);
        assertEquals(user.getUsername(), userProfile.getUsername());
        assertEquals(user.getName(), userProfile.getName());
        assertEquals(curBalance.getValue(), userProfile.getBalance());
        assertEquals("200", userProfile.getAllBalance());
        assertEquals(operations, userProfile.getOperations());
        assertEquals(goals.stream().map(GoalDto::new).toList(), userProfile.getGoals());
    }
}
