package com.example.protoFoodV2.service;

import com.example.protoFoodV2.apiModels.LocationApiModel;
import com.example.protoFoodV2.dataProvider.LocationsDataProvider;
import com.example.protoFoodV2.databaseModels.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocationManagementService {
    private final LocationsDataProvider locationsDataProvider;

    public void addNewLocation(LocationApiModel locationEntity) {
        locationsDataProvider.insert(locationEntity.toLocationEntity());
        System.out.println("Added new location : " + locationEntity);
    }
}
