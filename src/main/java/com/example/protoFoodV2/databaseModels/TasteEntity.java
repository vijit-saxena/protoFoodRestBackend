package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import lombok.NonNull;
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
    private String meal;
    private Integer quantity;
    private String paymentId;
    private LocalDateTime timeCreated;
}
