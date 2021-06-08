package com.example.desafio.Desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserFollowingException extends ResponseStatusException {
    public UserFollowingException() {
        super (HttpStatus.BAD_REQUEST, "User already is following");
    }
}
