package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.TasteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TasteDataProvider extends MongoRepository<TasteEntity, String> {
    List<TasteEntity> findTasteEntityByDateContainingAndMealContaining(String date, String meal);
}
