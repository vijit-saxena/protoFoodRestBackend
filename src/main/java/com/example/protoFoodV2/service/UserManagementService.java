package com.example.protoFoodV2.service;

import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.repos.UsersDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserManagementService {
    private final UsersDataProvider usersDataProvider;

    public List<UserEntity> listAllUsers() {
        return usersDataProvider.findAll();
    }

    public void createUser(UserEntity userEntity) {
        usersDataProvider.insert(userEntity);
        System.out.println("Added userEntity : " + userEntity);
    }
}
