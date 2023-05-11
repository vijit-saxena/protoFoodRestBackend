package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Meal;
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
    @Id private ObjectId tiffinId;
    @NonNull private final ObjectId userId;
    @NonNull private final LocalDateTime startDate;
    @NonNull private LocalDateTime endDate;
    @NonNull private final ObjectId subscriptionId;
    @NonNull private ObjectId locationId;
    private List<ObjectId> extras;
    private List<ObjectId> skips;
    @NonNull private Meal meal;
    @NonNull private final ObjectId paymentId;
    @NonNull private final LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
