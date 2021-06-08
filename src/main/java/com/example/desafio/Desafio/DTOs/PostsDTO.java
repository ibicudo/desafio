package com.example.desafio.Desafio.DTOs;

import com.example.desafio.Desafio.models.Post;

import java.util.List;

public class PostsDTO {
    private Integer userId;
    private List<Post> posts;

    public PostsDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
