package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.LocationsDataProvider;
import com.example.protoFoodV2.databaseModels.LocationEntity;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LocationManagementService {
    @Autowired
    private final LocationsDataProvider locationsDataProvider;

    public void addNewLocation(LocationEntity locationEntity) {
        locationsDataProvider.insert(locationEntity);
        System.out.println("Added new location : " + locationEntity);
    }

    public Optional<LocationEntity> fetchClosestLocation(double latitude, double longitude, String userPhoneNumber) {
        userPhoneNumber = Util.refactorPhoneNumber(userPhoneNumber);
        List<LocationEntity> allUserLocations = locationsDataProvider.findByUserId(userPhoneNumber);

        if (allUserLocations.isEmpty()) return Optional.empty();

        LocationEntity closestLocation = null;
        double minDist = Double.MAX_VALUE;
        for (LocationEntity location : allUserLocations) {
            double distance = Util.calculateLatLngDistance(
                    latitude,
                    longitude,
                    Double.parseDouble(location.getLatitude()),
                    Double.parseDouble(location.getLongitude()));
            if (distance < minDist) {
                minDist = distance;
                closestLocation = location;
            }
        }
        assert closestLocation != null;
        return Optional.of(closestLocation);
    }
}
