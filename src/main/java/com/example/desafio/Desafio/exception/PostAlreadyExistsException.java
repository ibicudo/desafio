package com.example.desafio.Desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostAlreadyExistsException extends ResponseStatusException {
    public PostAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "idPost already exists");
    }
}
