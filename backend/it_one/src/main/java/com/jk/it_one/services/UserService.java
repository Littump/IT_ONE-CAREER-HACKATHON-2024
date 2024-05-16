package com.jk.it_one.services;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import com.jk.it_one.repositories.UserRepository;
import com.jk.it_one.responceDtos.ProfileDto;
import com.jk.it_one.security.UserDetailsImpl;
import com.jk.it_one.utils.MoneyCalculator;
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

    @Autowired
    public UserService(UserRepository userRepository, BalanceService balanceService) {
        this.userRepository = userRepository;
        this.balanceService = balanceService;
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


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findMe(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public ProfileDto getUserProfile(Principal principal, Currency currency) {
        User user = findMe(principal);
        List<Balance> balances = balanceService.findUserBalances(user);
        String curBalance = balances.stream()
                .filter(b -> b.getCurrency().equals(currency))
                .map(Balance::getBalance)
                .findFirst().orElse("0");
        String allBalance = balances.stream()
                .map(Balance::getBalance)
                .reduce(MoneyCalculator::add)
                .orElse("0");
        //TODO
        return null;
    }
}
