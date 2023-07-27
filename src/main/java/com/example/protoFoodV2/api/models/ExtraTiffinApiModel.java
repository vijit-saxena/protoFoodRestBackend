package com.example.protoFoodV2.api.models;

import com.example.protoFoodV2.databaseModels.ExtraEntity;
import com.example.protoFoodV2.databaseModels.OrderEntity;
import com.example.protoFoodV2.databaseModels.PaymentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Getter
@ToString
public class ExtraTiffinApiModel {
    // Extra Tiffin Section
    @JsonProperty
    private String extraId;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String tiffinId;
    @JsonProperty
    private String date;
    @JsonProperty
    private String meal;//todo : ensure this value cannot be breakfast_lunch_dinner
    @JsonProperty
    private Integer quantity;
    @JsonProperty
    private String timeCreated;
    // Payment Section
    @JsonProperty
    private String paymentId;
    @JsonProperty
    private String amountInRs;
    @JsonProperty
    private String action;
    @JsonProperty
    private String status;

    public ExtraEntity toExtraEntity() {
        return new ExtraEntity(
                this.extraId,
                this.userId,
                this.tiffinId,
                this.date,
                this.meal,
                this.quantity,
                this.paymentId,
                this.timeCreated
        );
    }

    public PaymentEntity toPaymentEntity() {
        return new PaymentEntity(
                this.paymentId,
                this.userId,
                this.amountInRs,
                this.extraId,
                this.action,
                this.timeCreated,
                this.status);
    }

    public OrderEntity toOrderEntity() {
        return new OrderEntity(
                this.extraId,
                this.userId,
                this.timeCreated
        );
    }
}
