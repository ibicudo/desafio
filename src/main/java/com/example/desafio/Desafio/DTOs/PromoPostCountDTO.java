package com.example.desafio.Desafio.DTOs;

public class PromoPostCountDTO {
    private Integer userId;
    private String userName;
    private double promoproducts_count;

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

    public double getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(double promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
