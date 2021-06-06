package com.example.desafio.Desafio.controllers;

import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.repositories.PostRepository;
import com.example.desafio.Desafio.services.PostService;
import com.example.desafio.Desafio.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/newpost") //US 0005
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws IOException {
        return ResponseEntity.status(200).body(this.postService.createPost(post));
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postRepository.getPosts();
    }

    @GetMapping("/{userId}/postsByDate")
    public  List<Post> getPostsByDate(@PathVariable Integer userId) throws Exception {
        return  userService.getPostLastTwoWeeks(userId);
    }


}
