package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) throws IOException {
        return postRepository.createPost(post);
    }

    @Override
    public List<Post> getFollowedPost(Integer userId, String typeOrder) throws Exception {
        return postRepository.getFollowedPost(userId, typeOrder);
    }

    @Override
    public List<Post> getPostsLastTwoWeeks(Integer userId) throws Exception {
        return postRepository.getPostsLastTwoWeeks(userId);
    }
}
