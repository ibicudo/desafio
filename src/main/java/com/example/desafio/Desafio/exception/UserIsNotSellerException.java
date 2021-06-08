package com.example.desafio.Desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIsNotSellerException extends ResponseStatusException {
    public UserIsNotSellerException() {
        super(HttpStatus.BAD_REQUEST, "User can not following user!");
    }
}
