package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.exceptions.EntityNotFoundException;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Goal;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.UserRepository;
import com.jk.it_one.responce_dtos.OperationDto;
import com.jk.it_one.responce_dtos.ProfileDto;
import com.jk.it_one.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Transactional
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BalanceService balanceService;
    private final GoalService goalService;
    private final OperationsService operationsService;

    @Autowired
    public UserService(UserRepository userRepository, BalanceService balanceService, GoalService goalService, OperationsService operationsService) {
        this.userRepository = userRepository;
        this.balanceService = balanceService;
        this.goalService = goalService;
        this.operationsService = operationsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return UserDetailsImpl.build(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findMe(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("User %s not found".formatted(username)));
    }

    public ProfileDto getUserProfile(Principal principal, Currency currency) {
        User user = findMe(principal);
        Balance curBalance = balanceService.findUserBalance(user, currency);
        List<Balance> balances = balanceService.findUserBalances(user);
        List<OperationDto> operations = operationsService.findLast3(curBalance);
        List<Goal> goals = goalService.findAll(curBalance);
        return new ProfileDto(user, curBalance, balances, operations, goals);
    }
}
