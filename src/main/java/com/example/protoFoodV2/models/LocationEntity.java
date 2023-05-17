package com.example.protoFoodV2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LocationEntity {
    @Id
    private ObjectId locationId;
    private String buildingName;
    private String roomNumber;
    private String latitude;
    private String longitude;
    private String landmark;

    public LocationEntity(String buildingName, String roomNumber, String latitude, String longitude, String landmark) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.landmark = landmark;
    }
}

