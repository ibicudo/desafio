package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.DTOs.PromoPostCountDTO;
import com.example.desafio.Desafio.DTOs.PromoPostListDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.PostPromo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface PostService {
    Post createPost (Post post) throws IOException;
    PostPromo createPromoPost (PostPromo promoPost) throws IOException;
    List<Post> getFollowedPost (Integer userId, String typeOrder) throws Exception;
    List<Post>  getPostsLastTwoWeeks (Integer userId) throws Exception;
    PromoPostCountDTO countPromo (Integer userId ) throws Exception;
    PromoPostListDTO getListPromoPostBySeller (Integer userId) throws Exception;
}
