package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@Getter
public class LocationEntity {
    @Id
    @JsonProperty
    private String locationId;
    @JsonProperty
    private String buildingName;
    @JsonProperty
    private String roomNumber;
    @JsonProperty
    private String latitude;
    @JsonProperty
    private String longitude;
    @JsonProperty
    private String landmark;
    @JsonProperty
    private String shortName;
    @JsonProperty
    private String userId;
}

