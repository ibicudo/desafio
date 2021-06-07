package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.DTOs.PromoPostCountDTO;
import com.example.desafio.Desafio.DTOs.PromoPostListDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.PostPromo;
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
    public PostPromo createPromoPost(PostPromo promoPost) throws IOException {
        return postRepository.createPromoPost(promoPost);
    }

    @Override
    public List<Post> getFollowedPost(Integer userId, String typeOrder) throws Exception {
        return postRepository.getFollowedPost(userId, typeOrder);
    }

    @Override
    public List<Post> getPostsLastTwoWeeks(Integer userId) throws Exception {
        return postRepository.getPostsLastTwoWeeks(userId);
    }

    @Override
    public PromoPostCountDTO countPromo(Integer userId) throws Exception {
        return postRepository.countPromo(userId);
    }

    @Override
    public PromoPostListDTO getListPromoPostBySeller(Integer userId) throws Exception {
        return postRepository.getListPromoPostBySeller(userId);
    }


}
