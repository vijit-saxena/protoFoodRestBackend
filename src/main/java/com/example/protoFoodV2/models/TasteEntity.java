package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Meal;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class TasteEntity {
//    private ObjectId tasteId;
    private ObjectId userId;
    private Date date;
    private Meal meal;
    private Integer quantity;
    private ObjectId paymentId;
}
