package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.TasteEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TasteApiModel {
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;
    @JsonProperty
    private String quantity;
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String locationId;
    @JsonProperty
    private String timeCreated;

    public TasteEntity toTasteEntity() {
        return new TasteEntity(orderId, userId,
                date, meal, quantity, paymentId,
                locationId, timeCreated);
    }
}
