package com.example.desafio.Desafio.controllers;

import com.example.desafio.Desafio.DTOs.FollowDTO;
import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.User;
import com.example.desafio.Desafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById (@PathVariable Integer id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public SellerDTO followSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws Exception { //US 0001
        return userService.followSeller(userId, userIdToFollow);
    }

    @GetMapping("/{userId}/followers/count")
    public FollowersCountDTO getTotalFollowers (@PathVariable Integer userId) throws Exception { //US 0002
        return  userService.getTotalFollowers(userId);
    }

    @GetMapping("/{userId}/followers/list")
    public FollowListDTO getListFollowers(@PathVariable Integer userId) throws Exception {  //US 0003
        return  userService.getFollowersList(userId);
    }

    @GetMapping("/{userId}/followed/list")
    public FollowListDTO getListFollowed (@PathVariable Integer userId) throws Exception {  //US 0004
        return  userService.getUserFollowingList(userId);
    }

    @PostMapping("/{userId}/unfollow/{userIdToFollow}") //0007
    public SellerDTO unfollowSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws Exception { //US 0007
        return userService.unfollowSeller(userId, userIdToFollow);
    }

    @GetMapping("/{userId}/followersOrder/list")  //0008
    public List<FollowDTO> getFollowersOrder(@PathVariable Integer userId, @RequestParam String order) throws Exception {
        return userService.getFollowersOrder(userId, order);
    }

    @GetMapping("/{userId}/followedOrder/list")  //0008
    public List<FollowDTO> getFollowedOrder(@PathVariable Integer userId, @RequestParam String order) throws Exception {
        return userService.getFollowedOrder(userId, order);
    }

}
