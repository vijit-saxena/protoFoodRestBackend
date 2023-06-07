package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.TiffinEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TiffinApiModel {
    @JsonProperty
    private String orderId;
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
    private String meal;
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String timeCreated;
    @JsonProperty
    private String timeUpdated;
    @JsonProperty
    private List<String> extras;
    @JsonProperty
    private List<String> skips;

    public TiffinEntity toTiffinEntity() {
        return new TiffinEntity(orderId, userId, startDate,
                endDate, subscriptionId, locationId,
                extras, skips, meal, paymentId,
                timeCreated, timeUpdated);
    }
}
