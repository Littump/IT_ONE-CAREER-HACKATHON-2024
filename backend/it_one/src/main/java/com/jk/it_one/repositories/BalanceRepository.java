package com.jk.it_one.repositories;

import com.jk.it_one.enums.Currency;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByUserAndCurrency(User user, Currency currency);
    List<Balance> findBalancesByUser(User user);
}
