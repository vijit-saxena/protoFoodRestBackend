package com.example.protoFoodV2.service;

import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.dataProvider.UsersDataProvider;
import com.example.protoFoodV2.exceptions.EntityNotFoundException;
import com.example.protoFoodV2.exceptions.EntityType;
import com.example.protoFoodV2.exceptions.RenderableExceptionGenerator;
import com.example.protoFoodV2.utils.Util;
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
    }

    public UserEntity getUserByPhoneNumber(String userPhoneNumber) {
        String finalUserPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);

        return usersDataProvider.findUserEntityByContact(finalUserPhoneNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        EntityType.User,
                        finalUserPhoneNumber));
    }
}
