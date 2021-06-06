package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.DTOs.FollowDTO;
import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.User;
import com.example.desafio.Desafio.repositories.PostRepository;
import com.example.desafio.Desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public User findById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public SellerDTO followSeller (Integer userId, Integer userIdToFollow) throws Exception {
        return userRepository.followSeller(userId, userIdToFollow);
    }

    @Override
    public SellerDTO unfollowSeller(Integer userId, Integer userIdToFollow) throws Exception {
        return userRepository.unfollowSeller(userId, userIdToFollow);
    }

    @Override
    public FollowersCountDTO getTotalFollowers(Integer userId) throws Exception {
        return userRepository.getTotalFollowers(userId);
    }

    @Override
    public FollowListDTO getFollowersList(Integer userId) throws Exception {

        return userRepository.getFollowersList(userId);
    }

    @Override
    public FollowListDTO getUserFollowingList(Integer userId) throws Exception {
        return userRepository.getUserFollowingList(userId);
    }

    @Override
    public List<Post> getPostLastTwoWeeks(Integer id) throws Exception {
        User user = findById(id);
        List<Integer> sellers = user.getIdsFolllowed();
        List<Post> posts = postRepository.getPostOrdByDate("name_desc");
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
    public List<Post> getFollowedPost(Integer userId, String typeOrder) throws Exception {
        User user = findById(userId);
        List<Integer> sellers = user.getIdsFolllowed();
        List<Post> posts = postRepository.getPostOrdByDate(typeOrder);
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

    private boolean isLastTwoWeek(Date date){
        Date currentDate = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Date data2 = dateFormat.parse("1/06/2021");

        long diff = currentDate.getTime() - date.getTime();
        long days= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(days > 15) {
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public List<FollowDTO> getFollowersOrder(Integer userId, String typeOrder) throws Exception {
        return userRepository.getFollowersOrder(userId, typeOrder);
    }

    @Override
    public List<FollowDTO> getFollowedOrder(Integer userId, String typeOrder) throws Exception {
        return userRepository.getFollowedOrder(userId, typeOrder);
    }


}
