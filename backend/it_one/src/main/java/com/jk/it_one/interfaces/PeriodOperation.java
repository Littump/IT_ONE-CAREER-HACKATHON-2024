package com.jk.it_one.interfaces;

import com.jk.it_one.models.Balance;

import java.util.Date;

public interface PeriodOperation {
    Date getStartDay();
    Balance getBalance();
    void setBalance(Balance balance);
    String getValue();
}
