package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import lombok.NonNull;
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
    @NonNull private String meal;// ensure this value cannot be breakfast_lunch_dinner
    @NonNull private String paymentId;
    @NonNull private LocalDateTime timeCreated;
}
