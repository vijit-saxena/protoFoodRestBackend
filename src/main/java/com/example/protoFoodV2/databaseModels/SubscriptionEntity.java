package com.example.protoFoodV2.databaseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
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
    private String subscriptionId;
    private double discountInPercent;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer durationInDays;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private String mealType;

    @PersistenceCreator
    public SubscriptionEntity(double discountInPercent,
                              LocalDateTime startDateTime,
                              LocalDateTime endDateTime,
                              Integer durationInDays,
                              LocalDateTime timeCreated,
                              LocalDateTime timeUpdated,
                              String mealType) {
        this.discountInPercent = discountInPercent;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.durationInDays = durationInDays;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.mealType = mealType;
    }
}
