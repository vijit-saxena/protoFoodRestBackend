package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LocationEntity {
    @Id
    private String locationId;
    private String buildingName;
    private String roomNumber;
    private String latitude;
    private String longitude;
    private String landmark;
    private String shortName;
    private String userId;

    public LocationEntity(String buildingName, String roomNumber, String latitude, String longitude, String landmark, String shortName, String userId) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.landmark = landmark;
        this.shortName = shortName;
        this.userId = userId;
    }
}

