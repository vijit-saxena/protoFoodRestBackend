package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.SkipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SkipTiffinDataProvider extends MongoRepository<SkipEntity, String> {
    List<SkipEntity> findSkipEntityByDateContainingAndMealContaining(String date, String meal);
}
