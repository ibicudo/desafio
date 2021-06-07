package com.example.desafio.Desafio.controllers;

import com.example.desafio.Desafio.DTOs.PromoPostCountDTO;
import com.example.desafio.Desafio.DTOs.PromoPostListDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.PostPromo;
import com.example.desafio.Desafio.repositories.PostRepository;
import com.example.desafio.Desafio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("products")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/newpost") //US 0005
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws IOException {
        return ResponseEntity.status(200).body(this.postService.createPost(post));
    }

    @PostMapping("/newpromopost") //US 0010
    public ResponseEntity<PostPromo> createPostPromo(@RequestBody PostPromo promoPost) throws IOException {
        return ResponseEntity.status(200).body(this.postService.createPromoPost(promoPost));
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postRepository.getPosts();
    }

    @GetMapping("followed/{userId}/list")
    public List<Post> getFollowedPost (@PathVariable Integer userId, @RequestParam String order) throws Exception {//0009
        return postService.getFollowedPost(userId, order);
    }

    @GetMapping("/followed/{userId}/listTwoWeeks")//0006
    public List<Post> getPostsLastTwoWeeks (@PathVariable Integer userId) throws Exception {
        return postService.getPostsLastTwoWeeks(userId);
    }

    @GetMapping("/{userId}/countPromo")
    public PromoPostCountDTO countPromo (@PathVariable Integer userId) throws Exception { //0011
        return postService.countPromo(userId);
    }

    @GetMapping("/{userId}/list")
    public PromoPostListDTO getListPromoPostBySeller (@PathVariable Integer userId) throws Exception { //0012
        return postService.getListPromoPostBySeller(userId);
    }
}
