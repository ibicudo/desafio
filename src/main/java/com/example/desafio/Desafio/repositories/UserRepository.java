package com.example.desafio.Desafio.repositories;


import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowersListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.User;

public interface UserRepository {
    public User findById (Integer id) throws Exception;
    public SellerDTO followSeller (Integer userId, Integer userIdToFollow) throws Exception;
    public SellerDTO unfollowSeller (Integer userId, Integer userIdToFollow) throws Exception;
    User updateUser (Integer id, User user) throws Exception;
    FollowersCountDTO getTotalFollowers(Integer userId) throws Exception;
    FollowersListDTO getFollowersList (Integer id ) throws Exception;
    FollowersListDTO getUserFollowingList (Integer id ) throws Exception;
}
