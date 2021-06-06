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
import java.util.*;

@Repository
public class PostRepositoryImpl implements PostRepository{


    private File postsFile;
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<List<Post>> postTypeReferencce = new TypeReference<List<Post>>() {};

    public PostRepositoryImpl() throws FileNotFoundException {
        this.postsFile = ResourceUtils.getFile("src/main/resources/post.json");
    }

    @Override
    public Post createPost(Post post) throws IOException {
        List<Post> posts = mapper.readValue(postsFile, postTypeReferencce);
        Post postCreated = new Post(post.getUserId(), post.getId_post(), post.getDate(), post.getDetail(), post.getCategory(), post.getPrice());
        posts.add(postCreated);

        mapper.writeValue(postsFile, posts);
        return null;
    }

    @Override
    public Post getListPost(Integer userId) {


        return null;
    }

    @Override
    public List<Post> getPostOrdByDate() {
        List<Post> posts = getPosts();
        List<Post> postOrderByDate= new ArrayList<>();
        Map<Date, Post> map = new HashMap<>();

        for(Post post : posts){
            map.put(post.getDate(), post);
        }

        List<Date> chaves = new ArrayList<>(map.keySet());
        Collections.sort(chaves, Collections.reverseOrder());

        for (Date date : chaves) {
            postOrderByDate.add(map.get(date));
        }

        return postOrderByDate;
    }

    @Override
    public List<Post> getPosts() {
        List<Post> posts = null;
        try{
           posts = mapper.readValue(this.postsFile, postTypeReferencce);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
}
