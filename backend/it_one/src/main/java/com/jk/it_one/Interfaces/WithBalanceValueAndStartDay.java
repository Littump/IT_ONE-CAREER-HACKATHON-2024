package com.jk.it_one.Interfaces;

import com.jk.it_one.models.Balance;

import java.util.Date;

public interface WithBalanceValueAndStartDay<T> {
    Date getStartDay();
    Balance getBalance();
    void setBalance(Balance balance);
    String getValue();
    void setValue(String value);
}
