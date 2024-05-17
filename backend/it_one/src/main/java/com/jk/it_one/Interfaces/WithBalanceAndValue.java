package com.jk.it_one.Interfaces;

import com.jk.it_one.models.Balance;

public interface WithBalanceAndValue { //TODO maybe rename?
    Balance getBalance();
    void setBalance(Balance balance);
    String getValue();
    void setValue(String value);
}
