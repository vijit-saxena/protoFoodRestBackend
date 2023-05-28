package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersDataProvider extends MongoRepository<UserEntity, ObjectId> {
    Optional<UserEntity> findUserEntityByContact(String contact);


}
