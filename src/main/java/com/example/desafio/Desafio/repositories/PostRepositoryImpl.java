package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.models.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository{


    private File file;
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<List<Post>> postTypeReferencce = new TypeReference<List<Post>>() {};

    public PostRepositoryImpl() throws FileNotFoundException {
        this.file = ResourceUtils.getFile("src/main/resources/post.json");
    }

    @Override
    public Post createPost(Post post) throws IOException {
        List<Post> posts = mapper.readValue(file, postTypeReferencce);
        Post postCreated = new Post(post.getUserId(), post.getId_post(), post.getDate(), post.getDetail(), post.getCategory(), post.getPrice());
        posts.add(postCreated);

        mapper.writeValue(file, posts);
        return null;
    }

}
