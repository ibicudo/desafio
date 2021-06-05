package com.example.desafio.Desafio.DTOs;

import java.util.ArrayList;
import java.util.List;

public class FollowersListDTO {
    private Integer userId;
    private String userName;
    private List<FollowDTO> follow;

    public FollowersListDTO() {
        this.follow = new ArrayList<>();
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

    public List<FollowDTO> getFollow() {
        return follow;
    }

    public void setFollow(List<FollowDTO> follow) {
        this.follow = follow;
    }
}
