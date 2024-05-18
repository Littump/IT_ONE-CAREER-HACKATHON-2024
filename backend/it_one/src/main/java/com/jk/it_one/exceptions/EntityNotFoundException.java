package com.jk.it_one.exceptions;

public class EntityNotFoundException extends IllegalArgumentException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(long id) {
        super("Entity with id '%d' not found".formatted(id));
    }
}
