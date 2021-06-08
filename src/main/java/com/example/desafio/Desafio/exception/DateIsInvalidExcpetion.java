package com.example.desafio.Desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DateIsInvalidExcpetion extends ResponseStatusException {
    public DateIsInvalidExcpetion() {
        super(HttpStatus.BAD_REQUEST, "Date is invalid");
    }
}
