package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.models.Post;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PostRepository {
    Post createPost (Post post) throws IOException;
    Post getListPost (Integer userId);
    List<Post> getPostOrdByDate (String order);
    List<Post> getPosts ();
    List<Post>  getFollowedPost (Integer userId, String typeOrder) throws Exception;
    List<Post>  getPostsLastTwoWeeks (Integer userId) throws Exception;
}
