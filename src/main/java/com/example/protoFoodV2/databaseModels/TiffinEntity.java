package com.example.protoFoodV2.databaseModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@AllArgsConstructor
@Getter
public class TiffinEntity {
    @Id
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String startDate;
    @JsonProperty
    private String endDate;
    @JsonProperty
    private String subscriptionId;
    @JsonProperty
    private String locationId;
    @JsonProperty
    private List<String> extras;
    @JsonProperty
    private List<String> skips;
    @JsonProperty
    private String meal;
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String timeCreated;
    @JsonProperty
    private String timeUpdated;
}
