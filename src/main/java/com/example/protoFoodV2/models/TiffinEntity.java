package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Meal;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class TiffinEntity {
//    private ObjectId tiffinId;
    private final ObjectId userId;
    private final Date startDate;
    private Date endDate;
    private final ObjectId subscriptionId;
    private ObjectId locationId;
    private List<ObjectId> extras;
    private List<ObjectId> skips;
    private Meal meal;
    private final ObjectId paymentId;
    private final Date timeCreated;
    private Date timeUpdated;
}
