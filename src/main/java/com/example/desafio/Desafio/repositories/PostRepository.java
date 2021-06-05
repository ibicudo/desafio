package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.models.Post;

import java.io.IOException;

public interface PostRepository {
    Post createPost (Post post) throws IOException;
}
