package com.example.desafio.Desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsersAreEqualException extends ResponseStatusException {
    public UsersAreEqualException() {
        super(HttpStatus.BAD_REQUEST, "Users are equal");
    }
}
