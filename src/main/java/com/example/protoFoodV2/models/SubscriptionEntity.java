package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Meal;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document
@NonNull
public class SubscriptionEntity {
    @Id private ObjectId subscriptionId;
    private BigDecimal discountInPercent;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer durationInDays;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private Meal mealType;
}
