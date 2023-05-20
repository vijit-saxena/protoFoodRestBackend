package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.SubscriptionEntity;
import com.example.protoFoodV2.databaseModels.enums.Meal;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SubscriptionApiModel {
    @JsonProperty("discountInPercent")
    private double discountInPercent;
    @JsonProperty("startDateTime")
    private String startDateTime;
    @JsonProperty("endDateTime")
    private String endDateTime;
    @JsonProperty("durationInDays")
    private Integer durationInDays;
    @JsonProperty("mealType")
    private String mealType;

    public SubscriptionEntity toSubscriptionEntity() {
        return new SubscriptionEntity(
                discountInPercent,
                LocalDateTime.parse(startDateTime),
                LocalDateTime.parse(endDateTime),
                durationInDays,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Meal.valueOf(mealType));
    }
}
