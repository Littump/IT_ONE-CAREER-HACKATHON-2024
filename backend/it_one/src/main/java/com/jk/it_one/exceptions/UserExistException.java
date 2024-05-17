package com.jk.it_one.exceptions;

public class UserExistException extends IllegalArgumentException {
    public UserExistException(String username) {
        super("User with username '%s' already exist".formatted(username));
    }
}
