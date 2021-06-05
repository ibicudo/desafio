package com.example.desafio.Desafio.controllers;

import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowersListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.User;
import com.example.desafio.Desafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public FollowersListDTO getListFollowers(@PathVariable Integer userId) throws Exception {  //US 0003
        return  userService.getFollowersList(userId);
    }

    @GetMapping("/{userId}/followed/list")
    public FollowersListDTO getListFollowed (@PathVariable Integer userId) throws Exception {  //US 0004
        return  userService.getUserFollowingList(userId);
    }


}
