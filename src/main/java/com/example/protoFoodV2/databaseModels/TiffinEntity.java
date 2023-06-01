package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class TiffinEntity {
    @Id private String tiffinId;
    @NonNull private final String userId;
    @NonNull private final LocalDateTime startDate;
    @NonNull private LocalDateTime endDate;
    @NonNull private final String subscriptionId;
    @NonNull private String locationId;
    private List<ObjectId> extras;
    private List<ObjectId> skips;
    @NonNull private String meal;
    @NonNull private final String paymentId;
    @NonNull private final LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    // private LocalDateTime effectiveEndDate; --> when user skips some meals, endDate is pushed forward
}
