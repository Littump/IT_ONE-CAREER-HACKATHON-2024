package com.jk.it_one.exceptions;

public class NotEnoughMoneyException extends IllegalArgumentException {
    public NotEnoughMoneyException(long id) {
        super("Not enough money on balance with id '%d' for operation".formatted(id));
    }
}
