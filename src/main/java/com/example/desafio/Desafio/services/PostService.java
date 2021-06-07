package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.models.Post;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface PostService {
    Post createPost (Post post) throws IOException;
    List<Post> getFollowedPost (Integer userId, String typeOrder) throws Exception;
    List<Post>  getPostsLastTwoWeeks (Integer userId) throws Exception;
}
