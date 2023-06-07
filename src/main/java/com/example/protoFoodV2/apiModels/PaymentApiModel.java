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
    private String userId;
    @JsonProperty
    private String amountInRs;
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String action;
    @JsonProperty
    private String timeCreated;
    @JsonProperty
    private String status;

    public PaymentEntity toPaymentEntity() {
        return new PaymentEntity(paymentId, userId, amountInRs, orderId, action, timeCreated, status);
    }
}
