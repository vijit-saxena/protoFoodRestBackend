package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.ExtraEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ExtraTiffinApiModel {
    @JsonProperty
    private String extraId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;
    @JsonProperty
    private int quantity;
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String timeCreated;

    public ExtraEntity toExtraEntity(){
        return new ExtraEntity(extraId, userId, tiffinId,
                date, meal, quantity, paymentId, timeCreated);
    }
}
