package com.example.protoFoodV2.databaseModels;

import com.example.protoFoodV2.databaseModels.enums.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document
@NonNull
@AllArgsConstructor
public class SubscriptionEntity {
    @Id
    private ObjectId subscriptionId;
    private double discountInPercent;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer durationInDays;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private Meal mealType;

    @PersistenceCreator
    public SubscriptionEntity(double discountInPercent,
                              LocalDateTime startDateTime,
                              LocalDateTime endDateTime,
                              Integer durationInDays,
                              LocalDateTime timeCreated,
                              LocalDateTime timeUpdated,
                              Meal mealType) {
        this.discountInPercent = discountInPercent;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.durationInDays = durationInDays;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.mealType = mealType;
    }
}