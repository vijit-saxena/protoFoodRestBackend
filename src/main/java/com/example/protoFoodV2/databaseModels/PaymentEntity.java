package com.example.protoFoodV2.databaseModels;

import com.example.protoFoodV2.databaseModels.enums.Action;
import com.example.protoFoodV2.databaseModels.enums.PaymentStatus;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@NonNull
public class PaymentEntity {
    @Id private String paymentId;
    private String userId;
    private Action action;
    private LocalDateTime dateTime;
    private PaymentStatus status;
}
