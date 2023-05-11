package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Meal;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class SubscriptionEntity {
//    private ObjectId subscriptionId;
    private Double discountInPercent;
    private Date startDateTime;
    private Date endDateTime;
    private Integer durationInDays;
    private Date timeCreated;
    private Date timeUpdated;
    private Meal mealType;
}
