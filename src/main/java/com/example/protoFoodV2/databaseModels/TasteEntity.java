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
    @Id private String orderId;
    private String userId;
    private String date;
    private String meal;
    private String quantity;
    private String paymentId;
    private String locationId;
    private String timeCreatedInEpochMilli;

    public TasteEntity(String orderId, String userId,
                       String date, String meal, String quantity,
                       String paymentId, String locationId, String timeCreatedInEpochMilli) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.meal = meal;
        this.quantity = quantity;
        this.paymentId = paymentId;
        this.locationId = locationId;
        this.timeCreatedInEpochMilli = timeCreatedInEpochMilli;
    }
}
