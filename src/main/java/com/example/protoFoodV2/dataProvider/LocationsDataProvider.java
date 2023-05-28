package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.LocationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationsDataProvider extends MongoRepository<LocationEntity, ObjectId> {
}
