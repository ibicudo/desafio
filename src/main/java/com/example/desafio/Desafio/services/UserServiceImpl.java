package com.example.desafio.Desafio.services;

import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowersListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.User;
import com.example.desafio.Desafio.repositories.PostRepository;
import com.example.desafio.Desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public FollowersListDTO getFollowersList(Integer userId) throws Exception {

        return userRepository.getFollowersList(userId);
    }

    @Override
    public FollowersListDTO getUserFollowingList(Integer userId) throws Exception {
        return userRepository.getUserFollowingList(userId);
    }

    @Override
    public List<Post> getPostLastTwoWeeks(Integer id) throws Exception {
        User user = findById(id);
        List<Integer> sellers = user.getIdsFolllowed();
        List<Post> posts = postRepository.getPostOrdByDate();
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
}
