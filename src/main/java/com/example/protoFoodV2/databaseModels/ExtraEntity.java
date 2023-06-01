package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ExtraEntity {
    @Id private String extraId;
    private String userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String meal;// ensure this value cannot be breakfast_lunch_dinner
    @NonNull private Integer quantity;
    @NonNull private String paymentId;
    private LocalDateTime timeCreated;
}
