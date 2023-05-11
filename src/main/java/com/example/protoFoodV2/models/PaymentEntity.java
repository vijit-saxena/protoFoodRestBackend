package com.example.protoFoodV2.models;

import com.example.protoFoodV2.models.enums.Action;
import com.example.protoFoodV2.models.enums.PaymentStatus;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
public class PaymentEntity {
//    private ObjectId paymentId;
    private ObjectId userId;
    private Action action;
    private Date dateTime;
    private PaymentStatus status;
}
