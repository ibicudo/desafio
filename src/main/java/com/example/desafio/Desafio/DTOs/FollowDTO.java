package com.example.desafio.Desafio.DTOs;

public class FollowDTO {
    private Integer userId;
    private String userName;

    public FollowDTO() {
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
}
