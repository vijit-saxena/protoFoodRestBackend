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
public class ExtraEntity {
    @Id private ObjectId extraId;
    private ObjectId userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Meal meal;// ensure this value cannot be breakfast_lunch_dinner
    @NonNull private Integer quantity;
    @NonNull private ObjectId paymentId;
    private LocalDateTime timeCreated;
}
