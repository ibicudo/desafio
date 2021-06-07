package com.example.desafio.Desafio.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PostPromo {
    private Integer userId;
    private Integer id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public PostPromo() {
    }

    public PostPromo(Integer userId, Integer id_post, Date date, Product detail, int category, double price, boolean hasPromo, double discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    @Override
    public boolean equals (Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PostPromo postDto = (PostPromo) o;
        return id_post == postDto.id_post;

    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
