package com.example.protoFoodV2.models;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class LocationEntity {
//    private ObjectId locationId;
    private String buildingName;
    private String roomNumber;
    private String latitude;
    private String longitude;
    private String landmark;
}

