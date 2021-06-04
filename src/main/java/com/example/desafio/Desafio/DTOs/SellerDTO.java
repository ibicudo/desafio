package com.example.desafio.Desafio.DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellerDTO {
    private Integer id; //id seller
    private String name;
    private List<Integer> idsFollowers;

    public SellerDTO() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerDTO that = (SellerDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIdsFollowers() {
        return idsFollowers;
    }

    public void setIdsFollowers(List<Integer> idsFollowers) {
        this.idsFollowers = idsFollowers;
    }
}
