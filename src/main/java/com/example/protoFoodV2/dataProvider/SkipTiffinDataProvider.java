package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.SkipEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkipTiffinDataProvider extends MongoRepository<SkipEntity, String> {
}
