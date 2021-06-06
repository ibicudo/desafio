package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowersListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.User;

import java.util.List;

public interface UserService {
    public User findById (Integer id) throws Exception;
    public SellerDTO followSeller (Integer userId, Integer userIdToFollow) throws Exception;
    public SellerDTO unfollowSeller (Integer userId, Integer userIdToFollow) throws Exception;
    public FollowersCountDTO getTotalFollowers (Integer userId) throws Exception;
    FollowersListDTO getFollowersList (Integer id ) throws Exception;
    FollowersListDTO getUserFollowingList (Integer id ) throws Exception;
    List<Post> getPostLastTwoWeeks (Integer id) throws Exception;
}
