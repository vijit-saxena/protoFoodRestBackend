package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.ExtraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExtraTiffinDataProvider extends MongoRepository<ExtraEntity, String> {

    List<ExtraEntity> findExtraEntityByDateContainingAndMealContaining(String date, String meal);
}
