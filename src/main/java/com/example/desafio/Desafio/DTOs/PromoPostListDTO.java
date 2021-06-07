package com.example.desafio.Desafio.DTOs;

import com.example.desafio.Desafio.models.PostPromo;

import java.util.List;

public class PromoPostListDTO {
    private Integer userId;
    private String userName;
    private List<PromoPostDTO> posts;

    public PromoPostListDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PromoPostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PromoPostDTO> posts) {
        this.posts = posts;
    }
}
