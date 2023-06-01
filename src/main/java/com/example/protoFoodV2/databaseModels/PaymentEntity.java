package com.example.protoFoodV2.databaseModels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PaymentEntity {
    @Id private String paymentId;
    private String amount;
    private String orderId;
    private String action;
    private String paymentDateTime;
    private String status;

    public PaymentEntity(String paymentId, String amount, String orderId, String action, String paymentDateTime, String status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.action = action;
        this.paymentDateTime = paymentDateTime;
        this.status = status;
    }
}
