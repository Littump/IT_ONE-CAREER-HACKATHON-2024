package com.jk.it_one.interfaces;

import com.jk.it_one.models.Balance;

public interface Operation<T> {
    Balance getBalance();
    void setBalance(Balance balance);
    String getValue();
    void setValue(String value);
    void patch(T newValue);
}
