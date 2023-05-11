package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Meal;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class SkipEntity {
//    private ObjectId skipId;
    private ObjectId userId;
    private Date startDate;
    private Date endDate;
    private Meal meal;// ensure this value cannot be breakfast_lunch_dinner
    private ObjectId paymentId;
}