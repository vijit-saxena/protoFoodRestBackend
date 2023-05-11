package com.example.protoFoodV2.models;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LocationEntity {
    @Id private ObjectId locationId;
    @NonNull private String buildingName;
    @NonNull private String roomNumber;
    @NonNull private String latitude;
    @NonNull private String longitude;
    private String landmark;
}

