package com.example.desafio.Desafio.controllers;

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
    public SellerDTO followSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws Exception {
        return userService.followSeller(userId, userIdToFollow);
    }
}
