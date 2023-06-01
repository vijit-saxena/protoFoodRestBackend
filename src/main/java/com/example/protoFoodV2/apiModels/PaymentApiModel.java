package com.example.protoFoodV2.apiModels;

import com.example.protoFoodV2.databaseModels.PaymentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentApiModel {
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String amount;
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String action;
    @JsonProperty
    private String paymentDateTime;
    @JsonProperty
    private String status;

    public PaymentEntity toPaymentEntity() {
        return new PaymentEntity(paymentId, amount, orderId, action, paymentDateTime, status);
    }
}
