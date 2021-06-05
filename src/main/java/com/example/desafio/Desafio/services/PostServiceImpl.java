package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) throws IOException {
        return postRepository.createPost(post);
    }
}
