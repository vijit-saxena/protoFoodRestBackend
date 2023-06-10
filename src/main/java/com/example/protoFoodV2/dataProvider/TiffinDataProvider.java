package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.TiffinEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TiffinDataProvider extends MongoRepository<TiffinEntity, String> {
    Optional<TiffinEntity> findTiffinEntityByUserId(String contact);

    TiffinEntity findTiffinEntityByTiffinId(String tiffinId);
}
