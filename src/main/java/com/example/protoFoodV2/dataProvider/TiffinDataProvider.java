package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.TiffinEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TiffinDataProvider extends MongoRepository<TiffinEntity, String> {
}
