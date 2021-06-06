package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.FollowDTO;
import com.example.desafio.Desafio.DTOs.FollowListDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.Post;
import com.example.desafio.Desafio.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SellerRepositoryImpl sellerRepositoryImpl;

    private File userFile;
    private File sellersFile;
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<List<User>> userTypeReference = new TypeReference<List<User>>() {};


    public UserRepositoryImpl() throws FileNotFoundException {
        this.userFile = ResourceUtils.getFile("src/main/resources/users.json");
        this.sellersFile = ResourceUtils.getFile("src/main/resources/sellers.json");

    }

    @Override
    public User findById(Integer id) throws Exception {
        Optional<User> optionalUser = getUsers().stream().filter(users -> users.getId().equals(id)).findFirst();

        if (!optionalUser.isPresent()) {
            throw new Exception("Order does not exist");
        }

        return optionalUser.get();
    }

    public List<User> loadDataBase() throws IOException {

        List<User> users = null;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<List<User>>() {};

        users = objectMapper.readValue(this.userFile, typeRef);

        return users;
    }

    private List<User> getUsers() throws Exception {
        return mapper.readValue(this.userFile, userTypeReference);
    }

    @Override
    public SellerDTO followSeller (Integer userId, Integer userIdToFollow) throws Exception {
        SellerDTO sellerDTO = null;
        try{

            User userBuyer = findById(userId);
            sellerDTO = sellerRepositoryImpl.findById(userIdToFollow);

            idIsEqual(userId, userIdToFollow);
            userIsFollowing(userBuyer, sellerDTO);

            sellerDTO.getIdsFollowers().add(userId);
            userBuyer.getIdsFolllowed().add(userIdToFollow);
            sellerRepositoryImpl.updateSeller(userIdToFollow, sellerDTO);
            this.updateUser(userId,userBuyer);
        }catch (Exception e){
            e.printStackTrace();
        }

        return sellerDTO;

    }

    @Override
    public SellerDTO unfollowSeller(Integer userId, Integer userIdToFollow) throws Exception {
        SellerDTO sellerDTO = null;
        try{

            User userBuyer = findById(userId);
            sellerDTO = sellerRepositoryImpl.findById(userIdToFollow);

            idIsEqual(userId, userIdToFollow);
            if(!userBuyer.getIdsFolllowed().contains(sellerDTO.getId())){
                throw new Exception("User is not following seller");
            }

            sellerDTO.getIdsFollowers().remove(userId);
            userBuyer.getIdsFolllowed().remove(userIdToFollow);
            sellerRepositoryImpl.updateSeller(userIdToFollow, sellerDTO);
            this.updateUser(userId,userBuyer);
        }catch (Exception e){
            e.printStackTrace();
        }

        return sellerDTO;
    }

    private void idIsEqual (Integer userId, Integer userIdToFollow) throws Exception {
        if(userId.equals(userIdToFollow)){
            throw new Exception("Users are equal");
        }
    }

    private void userIsFollowing (User userBuyer, SellerDTO seller) throws Exception{
        if(userBuyer.getIdsFolllowed().contains(seller.getId())){
            throw new Exception("User is following");
        }
    }


    @Override
    public User updateUser(Integer id, User user) throws Exception {
        List<User> listUsers = loadDataBase();
        User storedUser = findById(id);
        int index = listUsers.indexOf(storedUser);

        listUsers.set(index,user);
        mapper.writeValue(this.userFile,listUsers);

        return user;
    }

    @Override
    public FollowersCountDTO getTotalFollowers(Integer userId) throws Exception {
        SellerDTO sellerDTO = sellerRepositoryImpl.findById(userId);
        FollowersCountDTO result = new FollowersCountDTO();

        result.setUserId(userId);
        result.setUserName(sellerDTO.getName());
        result.setFollowers_count(sellerDTO.getIdsFollowers().size());

        return result;
    }

    @Override
    public FollowListDTO getFollowersList(Integer userId) throws Exception {
        SellerDTO sellerDTO = sellerRepositoryImpl.findById(userId);
        List<Integer> users = sellerDTO.getIdsFollowers();
        FollowListDTO resultFollowers = new FollowListDTO();

        resultFollowers = setListFollow(users, resultFollowers);

        resultFollowers.setUserId(userId);
        resultFollowers.setUserName(sellerDTO.getName());

        return resultFollowers;
    }

    @Override
    public FollowListDTO getUserFollowingList(Integer userId) throws Exception {
        User user = findById(userId);
        List <Integer> users = user.getIdsFolllowed();
        FollowListDTO resultFollowed = new FollowListDTO();

        resultFollowed = setListFollow(users, resultFollowed);

        resultFollowed.setUserId(userId);
        resultFollowed.setUserName(user.getName());

        return resultFollowed;
    }

    @Override
    public List<FollowDTO> getFollowersOrder(Integer userId, String typeOrder) throws Exception {
        List<FollowDTO> followersOrder = new ArrayList<>();

        if(typeOrder != null){
            FollowListDTO seller = getFollowersList(userId);
            followersOrder = orderListFollow(seller, typeOrder);

        }
        return followersOrder;
    }

    @Override
    public List<FollowDTO> getFollowedOrder(Integer userId, String typeOrder) throws Exception {
        List<FollowDTO> followedOrder = new ArrayList<>();

        if(typeOrder != null){
            FollowListDTO user = getUserFollowingList(userId);
            followedOrder = orderListFollow(user, typeOrder);
        }
        return followedOrder;
    }

    private List<FollowDTO> orderListFollow (FollowListDTO user, String typeOrder){

        List<FollowDTO> followersOrder = new ArrayList<>();
        List<FollowDTO> followers = user.getFollow();
        Map<String, FollowDTO> map = new HashMap<>();

        for(FollowDTO followDTO: followers){
            map.put(followDTO.getUserName(), followDTO);
        }

        List<String> keys = new ArrayList<>(map.keySet());

        if(typeOrder.equals("name_asc")) {
            Collections.sort(keys);
        }else if(typeOrder.equals("name_desc")){
            Collections.sort(keys, Collections.reverseOrder());
        }

        for(String name : keys){
            followersOrder.add(map.get(name));
        }

        return followersOrder;
    }

    private FollowListDTO setListFollow (List<Integer> users, FollowListDTO resultFollow) throws Exception {
        int id ;
        User user = new User();
        FollowDTO followDTO = new FollowDTO();

        for(int i=0; i< users.size(); i++){
            id = users.get(i);
            user = findById(id);
            followDTO = new FollowDTO();
            followDTO.setUserId(id);
            followDTO.setUserName(user.getName());
            resultFollow.getFollow().add(i, followDTO);
        }
        return  resultFollow;
    }




}