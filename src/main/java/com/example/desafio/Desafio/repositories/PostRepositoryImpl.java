package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.PromoPostCountDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.PostPromo;
import com.example.desafio.Desafio.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
public class PostRepositoryImpl implements PostRepository{

    @Autowired
    UserRepository userRepository;

    private File postsFile;
    private File postsPromoFile;
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<List<Post>> postTypeReferencce = new TypeReference<List<Post>>() {};
    private TypeReference<List<PostPromo>> postPromoTypeReferencce = new TypeReference<List<PostPromo>>() {};

    public PostRepositoryImpl() throws FileNotFoundException {
        this.postsFile = ResourceUtils.getFile("src/main/resources/post.json");
        this.postsPromoFile = ResourceUtils.getFile("src/main/resources/postPromo.json");
    }

    @Override
    public Post createPost(Post post) throws IOException {
        List<Post> posts = mapper.readValue(postsFile, postTypeReferencce);
        Post postCreated = new Post(post.getUserId(), post.getId_post(), post.getDate(), post.getDetail(), post.getCategory(), post.getPrice());
        posts.add(postCreated);

        mapper.writeValue(postsFile, posts);
        return postCreated;
    }

    @Override
    public PostPromo createPromoPost(PostPromo promoPost) throws IOException {
        List<PostPromo> promoPosts= mapper.readValue(this.postsPromoFile, postPromoTypeReferencce);
        PostPromo postPromoCreated = new PostPromo(promoPost.getUserId(), promoPost.getId_post(), promoPost.getDate(), promoPost.getDetail(), promoPost.getCategory(), promoPost.getPrice(), promoPost.isHasPromo(), promoPost.getDiscount());
        promoPosts.add(postPromoCreated);

        mapper.writeValue(this.postsPromoFile, promoPosts);
        return postPromoCreated;
    }

    @Override
    public List<PostPromo> getListPromoPost() {
        List<PostPromo> posts = null;
        try{
            posts = mapper.readValue(this.postsPromoFile, postPromoTypeReferencce);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getPostOrdByDate(String order) {
        List<Post> posts = getPosts();
        List<Post> postOrderByDate= new ArrayList<>();
        Map<Date, Post> map = new HashMap<>();

        for(Post post : posts){
            map.put(post.getDate(), post);
        }
        List<Date> chaves = new ArrayList<>(map.keySet());
        if(order.equals("name_desc")) {
            Collections.sort(chaves);
        }else if(order.equals("name_asc")){
            Collections.sort(chaves, Collections.reverseOrder());
        }

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

    @Override
    public List<Post> getFollowedPost(Integer userId, String typeOrder) throws Exception {
        User user = userRepository.findById(userId);
        List<Integer> sellers = user.getIdsFolllowed();
        List<Post> posts = this.getPostOrdByDate(typeOrder);
        List<Post>  followedPosts = new ArrayList<>();

        for(Post post : posts){
            for(Integer i: sellers){
                if(i == post.getUserId()){
                    followedPosts.add(post);
                }
            }
        }
        return followedPosts;
    }

    @Override
    public List<Post> getPostsLastTwoWeeks(Integer userId) throws Exception {

        List<Post> listPosts = this.getFollowedPost(userId, "name_asc");
        List<Post> listPostsLastTwoWeeks = new ArrayList<>();

        for(Post posts : listPosts){
            Date date = posts.getDate();
            if(isLastTwoWeek(date)){
                listPostsLastTwoWeeks.add(posts);
            }
        }

        return listPostsLastTwoWeeks;
    }

    @Override
    public PromoPostCountDTO countPromo(Integer userId) throws Exception {
        User seller = userRepository.findById(userId);
        List<PostPromo> postPromo = this.getListPromoPost();
        PromoPostCountDTO result = new PromoPostCountDTO();

        int total =0;

        for(PostPromo post : postPromo){
            if(seller.getId().equals(post.getUserId())){
                total=total+1;
            }
        }

        result.setUserId(userId);
        result.setUserName(seller.getName());
        result.setPromoproducts_count(total);
        return result;
    }

    private boolean isLastTwoWeek(Date date){
        Date currentDate = Calendar.getInstance().getTime();

        long diff = currentDate.getTime() - date.getTime();
        long days= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(days > 15) {
            return false;
        }
        else{
            return true;
        }
    }
}
