package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.TasteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TasteDataProvider extends MongoRepository<TasteEntity, String> {
}
