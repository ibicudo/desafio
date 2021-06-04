package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.User;

public interface UserService {
    public User findById (Integer id) throws Exception;
    public SellerDTO followSeller (Integer userId, Integer userIdToFollow) throws Exception;
    public FollowersCountDTO getTotalFollowers (Integer userId) throws Exception;
}
