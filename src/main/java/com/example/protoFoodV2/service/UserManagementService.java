package com.example.protoFoodV2.service;

import com.example.protoFoodV2.models.UserEntity;
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
}
