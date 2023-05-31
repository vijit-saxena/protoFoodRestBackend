package com.example.protoFoodV2.service;

import com.example.protoFoodV2.databaseModels.UserEntity;
import com.example.protoFoodV2.dataProvider.UsersDataProvider;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserManagementService {
    private final UsersDataProvider usersDataProvider;

    public List<UserEntity> listAllUsers() {
        return usersDataProvider.findAll();
    }

    public void createUser(UserEntity userEntity) {
        usersDataProvider.insert(userEntity); // It auto throws exceptions
        System.out.println("Added userEntity : " + userEntity);
    }

    public void userAlreadyExists(String userPhoneNumber) {
        String finalUserPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        usersDataProvider.findUserEntityByContact(finalUserPhoneNumber).ifPresent(
                user -> {
                    throw new KeyAlreadyExistsException("User with phone number : "
                            + finalUserPhoneNumber + " already exists");
                }
        );
    }

    public Optional<UserEntity> getUserByPhoneNumber(String userPhoneNumber) {
        String finalUserPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        Optional<UserEntity> user = usersDataProvider.findUserEntityByContact(finalUserPhoneNumber);
        return user;
    }
}
