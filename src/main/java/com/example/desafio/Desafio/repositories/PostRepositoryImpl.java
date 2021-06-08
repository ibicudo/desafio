package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.PromoPostCountDTO;
import com.example.desafio.Desafio.DTOs.PromoPostDTO;
import com.example.desafio.Desafio.DTOs.PromoPostListDTO;
import com.example.desafio.Desafio.exception.DateIsInvalidExcpetion;
import com.example.desafio.Desafio.exception.PostAlreadyExistsException;
import com.example.desafio.Desafio.exception.UserIsNotSellerException;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
    public Post createPost(Post post) throws Exception {
        List<Post> posts = mapper.readValue(postsFile, postTypeReferencce);

        isSeller(post.getUserId());
        sellerAlreadyPostedThis(post.getUserId(), post.getId_post());

        Post postCreated = new Post(post.getUserId(), post.getId_post(), post.getDate(), post.getDetail(), post.getCategory(), post.getPrice());
        posts.add(postCreated);

        mapper.writeValue(postsFile, posts);
        return postCreated;
    }

    @Override
    public PostPromo createPromoPost(PostPromo promoPost) throws Exception {
        List<PostPromo> promoPosts= mapper.readValue(this.postsPromoFile, postPromoTypeReferencce);

        isSeller(promoPost.getUserId());
        PostPromo postPromoCreated = new PostPromo(promoPost.getUserId(), promoPost.getId_post(), promoPost.getDate(), promoPost.getDetail(), promoPost.getCategory(), promoPost.getPrice(), promoPost.isHasPromo(), promoPost.getDiscount());
        promoPosts.add(postPromoCreated);


        mapper.writeValue(this.postsPromoFile, promoPosts);
        return postPromoCreated;
    }

    private void isSeller (Integer id) throws Exception {
        User user = userRepository.findById(id);
        if (!user.isSeller()){
            throw new UserIsNotSellerException();
        }
    }

    private void sellerAlreadyPostedThis(Integer id, Integer idPost){
        List<Post> list = this.getPosts();
        for(Post post: list){
            if(post.getUserId().equals(id) && post.getId_post().equals(idPost)){
                throw new PostAlreadyExistsException();
            }
        }

    }

    @Override
    public List<PostPromo> getListPromoPost(Integer userId) throws Exception {
        User seller = userRepository.findById(userId);
        List<PostPromo> posts = null;
        List<PostPromo> postsPromoUser = new ArrayList<>();

        try{
            posts = mapper.readValue(this.postsPromoFile, postPromoTypeReferencce);

        }catch (Exception e) {
            e.printStackTrace();
        }

        for(PostPromo post : posts){
            if(seller.getId().equals(post.getUserId())){
               postsPromoUser.add(post);
            }
        }


        return postsPromoUser;
    }

    @Override
    public PromoPostListDTO getListPromoPostBySeller(Integer userId) throws Exception {
        User seller = userRepository.findById(userId);
        List<PostPromo> listPosts = this.getListPromoPost(userId);
        List<PromoPostDTO> promoPost  = new ArrayList<>();
        PromoPostDTO promoPostDTO = new PromoPostDTO();
        PromoPostListDTO promoPostListDTO = new PromoPostListDTO();

        for(PostPromo posts : listPosts) {
            promoPostDTO = new PromoPostDTO();
            promoPostDTO.setId_post(posts.getId_post());
            promoPostDTO.setDate(posts.getDate());
            promoPostDTO.setDetail(posts.getDetail());
            promoPostDTO.setCategory(posts.getCategory());
            promoPostDTO.setPrice(posts.getPrice());
            promoPostDTO.setHasPromo(posts.isHasPromo());
            promoPostDTO.setDiscount(posts.getDiscount());
            promoPost.add(promoPostDTO);
        }

        promoPostListDTO.setUserId(seller.getId());
        promoPostListDTO.setUserName(seller.getName());
        promoPostListDTO.setPosts(promoPost);

        return promoPostListDTO;
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
            Collections.sort(chaves, Collections.reverseOrder());
        }else if(order.equals("name_asc")){
            Collections.sort(chaves);
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
        List<PostPromo> postPromo = this.getListPromoPost(userId);
        PromoPostCountDTO result = new PromoPostCountDTO();


        result.setUserId(userId);
        result.setUserName(seller.getName());
        result.setPromoproducts_count(postPromo.size());
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
