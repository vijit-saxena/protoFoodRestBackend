package com.example.protoFoodV2.databaseModels;

import com.example.protoFoodV2.databaseModels.enums.Meal;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class SkipEntity {
    @Id private String skipId;
    @NonNull private String userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @NonNull private Meal meal;// ensure this value cannot be breakfast_lunch_dinner
    @NonNull private String paymentId;
    @NonNull private LocalDateTime timeCreated;
}
