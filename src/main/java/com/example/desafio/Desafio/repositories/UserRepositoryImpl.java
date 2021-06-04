package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.FollowersCountDTO;
import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.example.desafio.Desafio.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


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
    public SellerDTO followSeller (Integer userId, Integer userIdToFollow) throws Exception { //US 0001
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

    private void idIsEqual (Integer userId, Integer userIdToFollow) throws Exception {
        if(userId.equals(userIdToFollow)){
            throw new Exception("User is following");
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


}