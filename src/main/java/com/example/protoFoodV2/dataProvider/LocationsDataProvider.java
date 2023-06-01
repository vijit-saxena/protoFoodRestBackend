package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.LocationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationsDataProvider extends MongoRepository<LocationEntity, String> {
    List<LocationEntity> findByUserId(String userId);

    List<LocationEntity> findByLandmark(String landmark);
}
