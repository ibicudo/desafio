package com.example.desafio.Desafio.controllers;

import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("products")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/newpost") //US 0005
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws IOException {
        return ResponseEntity.status(200).body(this.postService.createPost(post));
    }
}
