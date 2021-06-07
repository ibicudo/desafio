package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.PromoPostCountDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.PostPromo;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PostRepository {
    Post createPost (Post post) throws IOException;
    PostPromo createPromoPost (PostPromo promoPost) throws IOException;
    List<PostPromo> getListPromoPost ();
    List<Post> getPostOrdByDate (String order);
    List<Post> getPosts ();
    List<Post>  getFollowedPost (Integer userId, String typeOrder) throws Exception;
    List<Post>  getPostsLastTwoWeeks (Integer userId) throws Exception;
    PromoPostCountDTO countPromo (Integer userId ) throws Exception;
}
