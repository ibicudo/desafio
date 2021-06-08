package com.example.desafio.Desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIsNotFollowing extends ResponseStatusException {
    public UserIsNotFollowing(){
        super(HttpStatus.BAD_REQUEST, "User is not following");
    }
}
