package com.example.protoFoodV2.dataProvider;

import com.example.protoFoodV2.databaseModels.LocationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LocationsDataProvider extends MongoRepository<LocationEntity, String> {
    List<LocationEntity> findByUserId(String userId);

    Optional<LocationEntity> findLocationEntityByLocationId(String locationId);
}
