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
public class SkipEntity {
    @Id private String skipId;
    private String userId;
    private String date;
    private String meal;// ensure this value cannot be breakfast_lunch_dinner
    private String tiffinId;
    private String timeCreated;
}
