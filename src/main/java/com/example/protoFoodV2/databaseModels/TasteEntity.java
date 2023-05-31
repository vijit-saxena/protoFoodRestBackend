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
@NonNull
public class TasteEntity {
    @Id private String tasteId;
    private String userId;
    private LocalDateTime date;
    private Meal meal;
    private Integer quantity;
    private String paymentId;
    private LocalDateTime timeCreated;
}
