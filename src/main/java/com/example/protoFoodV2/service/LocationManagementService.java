package com.example.protoFoodV2.service;

 
import com.example.protoFoodV2.dataProvider.LocationsDataProvider;
import com.example.protoFoodV2.databaseModels.LocationEntity;
import com.example.protoFoodV2.exceptions.EntityNotFoundException;
import com.example.protoFoodV2.exceptions.EntityType;
import com.example.protoFoodV2.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class LocationManagementService {
    @Autowired
    private final LocationsDataProvider locationsDataProvider;

    public void addNewLocation(LocationEntity locationEntity) {
        locationsDataProvider.insert(locationEntity);
    }

    public LocationEntity getLocationById(String locationId) {
        return locationsDataProvider.findLocationEntityByLocationId(locationId)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.Location, locationId));
    }
    public List<LocationEntity> fetchUserAllLocations(String userPhoneNumber) {
        return locationsDataProvider.findByUserId(userPhoneNumber);
    }

    public LocationEntity fetchClosestLocation(double latitude, double longitude, String userPhoneNumber) {
        List<LocationEntity> allUserLocations = locationsDataProvider.findByUserId(userPhoneNumber);
        log.info("Retrieved user's all locations. Searching closest location to LatLng({},{})", latitude, longitude);

        if (allUserLocations.size() == 1) return allUserLocations.get(0);

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
        return closestLocation;
    }
}
