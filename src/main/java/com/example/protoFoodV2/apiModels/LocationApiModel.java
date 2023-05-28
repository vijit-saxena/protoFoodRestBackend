package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.LocationEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationApiModel {
    @JsonProperty("buildingName")
    private String buildingName;
    @JsonProperty("roomNumber")
    private String roomNumber;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("landmark")
    private String landmark;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("userId")
    private String userId;

    public LocationEntity toLocationEntity() {
        return new LocationEntity(buildingName, roomNumber, latitude, longitude, landmark, shortName, userId);
    }
}
