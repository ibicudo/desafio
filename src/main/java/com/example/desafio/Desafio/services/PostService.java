package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.models.Post;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface PostService {
    Post createPost (Post post) throws IOException;
}
