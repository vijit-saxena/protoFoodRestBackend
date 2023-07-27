package com.example.protoFoodV2.api.models;

import com.example.protoFoodV2.databaseModels.OrderEntity;
import com.example.protoFoodV2.databaseModels.SkipEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@AllArgsConstructor
@ToString
public class SkipTiffinApiModel {
    // Skip Tiffin Section
    @JsonProperty
    private String skipId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;// ensure this value cannot be breakfast_lunch_dinner
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String timeCreated;

    public SkipEntity toSkipEntity() {
        return new SkipEntity(
                this.skipId,
                this.userId,
                this.date,
                this.meal,
                this.tiffinId,
                this.timeCreated
        );
    }

    public OrderEntity toOrderEntity() {
        return new OrderEntity(
                this.skipId,
                this.userId,
                this.timeCreated
        );
    }
}
