package com.example.protoFoodV2.databaseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@AllArgsConstructor
public class ExtraEntity {
    @Id private String extraId;
    private String userId;
    private String tiffinId;
    private String date;
    private String meal;//todo : ensure this value cannot be breakfast_lunch_dinner
    private Integer quantity;
    private String paymentId;
    private String timeCreated;
}
