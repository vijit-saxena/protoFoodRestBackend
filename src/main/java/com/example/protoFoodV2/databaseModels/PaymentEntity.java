package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PaymentEntity {
    @Id private String paymentId;
    private String userId;
    private String amountInRs;
    private String orderId;
    private String action;
    private String timeCreatedInEpochMilli;
    private String status;

    public PaymentEntity(String paymentId, String userId,
                         String amountInRs, String orderId, String action,
                         String timeCreatedInEpochMilli, String status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amountInRs = amountInRs;
        this.orderId = orderId;
        this.action = action;
        this.timeCreatedInEpochMilli = timeCreatedInEpochMilli;
        this.status = status;
    }
}
